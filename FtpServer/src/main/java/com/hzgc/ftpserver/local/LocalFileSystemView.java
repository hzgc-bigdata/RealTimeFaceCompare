package com.hzgc.ftpserver.local;

import org.apache.ftpserver.filesystem.nativefs.impl.NativeFtpFile;
import org.apache.ftpserver.ftplet.FileSystemView;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpFile;
import org.apache.ftpserver.ftplet.User;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.Serializable;

public class LocalFileSystemView implements FileSystemView, Serializable {
    private final Logger LOG = Logger.getLogger(LocalFileSystemView.class);

    private String rootDir;

    private String currDir;

    private User user;

    private boolean caseInsensitive = false;

    protected LocalFileSystemView(User user) throws FtpException {
        this(user, false);
    }

    public LocalFileSystemView(User user, boolean caseInsensitive)
            throws FtpException {
        if (user == null) {
            throw new IllegalArgumentException("user can not be null");
        }
        if (user.getHomeDirectory() == null) {
            throw new IllegalArgumentException(
                    "User home directory can not be null");
        }

        this.caseInsensitive = caseInsensitive;

        // add last '/' if necessary
        String rootDir = user.getHomeDirectory();
        rootDir = LocalFtpFile.normalizeSeparateChar(rootDir);
        if (!rootDir.endsWith("/")) {
            rootDir += '/';
        }

        LOG.debug("Native filesystem view created for user \"{}\" with root \"{}\"" + user.getName() + rootDir);

        this.rootDir = rootDir;

        this.user = user;

        currDir = "/";
    }

    public FtpFile getHomeDirectory() {
        return new LocalFtpFile("/", new File(rootDir), user);
    }

    /**
     * Get the current directory.
     */
    public FtpFile getWorkingDirectory() {
        FtpFile fileObj = null;
        if (currDir.equals("/")) {
            fileObj = new LocalFtpFile("/", new File(rootDir), user);
        } else {
            File file = new File(rootDir, currDir.substring(1));
            fileObj = new LocalFtpFile(currDir, file, user);

        }
        return fileObj;
    }

    /**
     * Get file object.
     */
    public FtpFile getFile(String file) {

        // get actual file object
        String physicalName = NativeFtpFile.getPhysicalName(rootDir,
                currDir, file, caseInsensitive);
        File fileObj = new File(physicalName);

        // strip the root directory and return
        String userFileName = physicalName.substring(rootDir.length() - 1);
        return new LocalFtpFile(userFileName, fileObj, user);
    }

    /**
     * Change directory.
     */
    public boolean changeWorkingDirectory(String dir) {

        // not a directory - return false
        dir = NativeFtpFile.getPhysicalName(rootDir, currDir, dir,
                caseInsensitive);
        File dirObj = new File(dir);
        if (!dirObj.isDirectory()) {
            return false;
        }

        // strip user root and add last '/' if necessary
        dir = dir.substring(rootDir.length() - 1);
        if (dir.charAt(dir.length() - 1) != '/') {
            dir = dir + '/';
        }

        currDir = dir;
        return true;
    }

    /**
     * Is the file content random accessible?
     */
    public boolean isRandomAccessible() {
        return true;
    }

    /**
     * Dispose file system view - does nothing.
     */
    public void dispose() {
    }
}
