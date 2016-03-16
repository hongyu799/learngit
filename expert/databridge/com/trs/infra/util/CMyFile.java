/**
 * Created:         2001.10
 * Last Modified:   2006.01.16
 * Description:
 *      class CMyFile ���� �ļ�����ͨ�ú����Ķ����ʵ��
 * Update Log:
 * 		2006.04.13	FuChengrui
 * 		�����˷���extractMainFileName()���Գ�ȡ���ļ�����
 * 		�����˷���excludeFileExt()����ȥ���ļ�������չ�����֣�
 * 		2006.01.16	FuChengrui
 * 		�޸��˺���makeDir(String,boolean)����Ϊ���壬�������ΪĿ��Ŀ¼�Ѿ����ڣ�
 * 		���µĵ��ô���Ŀ¼��APIʧ�ܣ�����Ȼ����<code>true</code>������Ŀ¼������
 * 		�ú��������ġ�
 */

package com.trs.infra.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Title: TRS ����Э��ƽ̨��TRS WCM�� Description: class CMyFile ���� �ļ�����ͨ�ú����Ķ����ʵ��
 * Copyright: Copyright (c) 2001-2002 TRS��Ϣ�������޹�˾ Company:
 * TRS��Ϣ�������޹�˾(www.trs.com.cn)
 * 
 * @author TRS��Ϣ�������޹�˾
 * @version 1.0
 */
public class CMyFile {
    // caohui@2004-07-19 ������־�������
    private static org.apache.log4j.Logger m_oLogger = org.apache.log4j.Logger
            .getLogger(CMyFile.class);

    /**
     * ���캯��
     */
    public CMyFile() {

    }

    // ==================================================================
    // �ļ����Ʒֽ�ļ������ߺ���

    /**
     * ���ָ���ļ��Ƿ����
     * 
     * @param _sPathFileName
     *            �ļ�����(��·����
     * @return �����ڣ��򷵻�true�����򣬷���false
     */
    public static boolean fileExists(String _sPathFileName) {
        File file = new File(_sPathFileName);
        return file.exists();
    }

    /**
     * ���ָ���ļ���·���Ƿ����
     * 
     * @param _sPathFileName
     *            �ļ�����(��·����
     * @return �����ڣ��򷵻�true�����򣬷���false
     */
    public static boolean pathExists(String _sPathFileName) {
        String sPath = extractFilePath(_sPathFileName);
        return fileExists(sPath);
    }

    /**
     * ���ļ�������·������·��+�ļ���������ȡ�ļ���(������չ��) <br>
     * �磺d:\path\file.ext --> file.ext
     * 
     * @param _sFilePathName
     * @return
     */
    public static String extractFileName(String _sFilePathName) {
        return extractFileName(_sFilePathName, File.separator);
    }

    /**
     * ���ļ�������·������·��+�ļ���������ȡ�ļ���(������չ��) <br>
     * �磺d:\path\file.ext --> file.ext
     * 
     * @param _sFilePathName
     *            ȫ�ļ�·����
     * @param _sFileSeparator
     *            �ļ��ָ���
     * @return
     */
    public static String extractFileName(String _sFilePathName,
            String _sFileSeparator) {
        int nPos = -1;
        if (_sFileSeparator == null) {
            nPos = _sFilePathName.lastIndexOf(File.separatorChar);
            if (nPos < 0) {
                nPos = _sFilePathName
                        .lastIndexOf(File.separatorChar == '/' ? '\\' : '/');
            }
        } else {
            nPos = _sFilePathName.lastIndexOf(_sFileSeparator);
        }

        if (nPos < 0) {
            return _sFilePathName;
        }

        return _sFilePathName.substring(nPos + 1);
    }

    // caohui@020513
    /**
     * ��EB·����ַ����ȡ: �ļ���
     * 
     * @param _sFilePathName
     * @return
     */
    public static String extractHttpFileName(String _sFilePathName) {
        int nPos = _sFilePathName.lastIndexOf("/");
        return _sFilePathName.substring(nPos + 1);
    }

    /**
     * ���ļ�������·������·��+�ļ���������ȡ:���ļ�����������·������չ����
     * 
     * @param _sFilePathName
     * @return
     */
    public static String extractMainFileName(String _sFilePathName) {
        String sFileMame = extractFileName(_sFilePathName);
        int nPos = sFileMame.lastIndexOf('.');
        if (nPos > 0) {
            return sFileMame.substring(0, nPos);
        }
        return sFileMame;
    }

    /**
     * �ų��ļ�����չ��,ֻ����·��(�������)�����ļ���
     * 
     * @param sFileMame
     * @return
     */
    public static String excludeFileExt(String sFileMame) {
        int nPos = sFileMame.lastIndexOf('.');
        if (nPos > 0) {
            return sFileMame.substring(0, nPos);
        }
        return sFileMame;
    }

    /**
     * ���ļ�������·������·��+�ļ���������ȡ: �ļ���չ��
     * 
     * @param _sFilePathName
     * @return
     */
    public static String extractFileExt(String _sFilePathName) {
        int nPos = _sFilePathName.lastIndexOf('.');
        return (nPos >= 0 ? _sFilePathName.substring(nPos + 1) : "");
    }

    /**
     * ���ļ�������·������·��+�ļ���������ȡ ·����������Drive+Directroy )
     * 
     * @param _sFilePathName
     * @return
     */
    public static String extractFilePath(String _sFilePathName) {
        int nPos = _sFilePathName.lastIndexOf('/');
        if (nPos < 0) {
            nPos = _sFilePathName.lastIndexOf('\\');
        }
        return (nPos >= 0 ? _sFilePathName.substring(0, nPos + 1) : "");
    }

    /**
     * ���ļ�/·������ת��Ϊ����·����
     * 
     * @param _sFilePathName
     *            �ļ�����·����
     * @return
     */
    public static String toAbsolutePathName(String _sFilePathName) {
        File file = new File(_sFilePathName);
        return file.getAbsolutePath();
    }

    /**
     * ���ļ�������·������·��+�ļ���������ȡ�ļ����������� <br>
     * ע�⣺�����������͵��ļ�����ʾ <br>
     * [1] d:\path\filename.ext --> return "d:" <br>
     * [2] \\host\shareDrive\shareDir\filename.ext --> return
     * "\\host\shareDrive"
     * 
     * @param _sFilePathName
     * @return
     */
    public static String extractFileDrive(String _sFilePathName) {
        int nPos;
        int nLen = _sFilePathName.length();

        // ����Ƿ�Ϊ "d:\path\filename.ext" ��ʽ
        if ((nLen > 2) && (_sFilePathName.charAt(1) == ':'))
            return _sFilePathName.substring(0, 2);

        // ����Ƿ�Ϊ "\\host\shareDrive\shareDir\filename.ext" ��ʽ
        if ((nLen > 2) && (_sFilePathName.charAt(0) == File.separatorChar)
                && (_sFilePathName.charAt(1) == File.separatorChar)) {
            nPos = _sFilePathName.indexOf(File.separatorChar, 2);
            if (nPos >= 0)
                nPos = _sFilePathName.indexOf(File.separatorChar, nPos + 1);
            return (nPos >= 0 ? _sFilePathName.substring(0, nPos)
                    : _sFilePathName);
        }

        return "";
    }// END:extractFileDrive

    /**
     * ɾ��ָ�����ļ�
     * 
     * @param _sFilePathName
     *            ָ�����ļ���
     * @return
     */
    public static boolean deleteFile(String _sFilePathName) {
        File file = new File(_sFilePathName);
        return file.exists() ? file.delete() : false;
    }

    // =======================================================================
    // Ŀ¼��������

    /**
     * ����Ŀ¼
     * 
     * @param _sDir
     *            Ŀ¼����
     * @param _bCreateParentDir
     *            �����Ŀ¼�����ڣ��Ƿ񴴽���Ŀ¼
     * @return
     */
    public static boolean makeDir(String _sDir, boolean _bCreateParentDir) {
        boolean zResult = false;
        File file = new File(_sDir);
        if (_bCreateParentDir)
            zResult = file.mkdirs(); // �����Ŀ¼�����ڣ��򴴽����б���ĸ�Ŀ¼
        else
            zResult = file.mkdir(); // �����Ŀ¼�����ڣ���������
        if (!zResult)
            zResult = file.exists();
        return zResult;
    }

    /**
     * ɾ��ָ����Ŀ¼�����е��ļ� ע�⣺���ļ���Ŀ¼����ʹ�ã�ɾ��������ʧ�ܡ�
     * 
     * @param _sDir
     *            Ŀ¼��
     * @param _bDeleteChildren
     *            �Ƿ�ɾ������Ŀ¼�����ļ�����ʡ�ԣ�Ĭ�ϲ�ɾ����
     * @return <code>true</code> if the directory exists and has been deleted
     *         successfully.
     * @deprecated to use deleteDir(String _sPath) or deleteDir(File _path)
     *             instead.
     */
    public static boolean deleteDir(String _sDir, boolean _bDeleteChildren) {
        File file = new File(_sDir);
        if (!file.exists())
            return false;

        if (_bDeleteChildren) { // ɾ����Ŀ¼�������ļ�
            File[] files = file.listFiles(); // ȡĿ¼���ļ�����Ŀ¼�б�
            File aFile;
            for (int i = 0; i < files.length; i++) {
                aFile = files[i];
                if (aFile.isDirectory()) {
                    deleteDir(aFile);
                } else {
                    aFile.delete();
                }
            }// end for
        }// end if
        return file.delete(); // ɾ����Ŀ¼
    }// END:deleteDir

    /**
     * Deletes a file path, and all the files and subdirectories in this path
     * will also be deleted.
     * 
     * @param _sPath
     *            the specified path.
     * @return <code>true</code> if the path exists and has been deleted
     *         successfully; <code>false</code> othewise.
     */
    public static boolean deleteDir(String _sPath) {
        File path = new File(_sPath);
        return deleteDir(path);
    }

    /**
     * Deletes a file path, and all the files and subdirectories in this path
     * will also be deleted.
     * 
     * @param _path
     *            the specified path.
     * @return <code>true</code> if the path exists and has been deleted
     *         successfully; <code>false</code> othewise.
     */
    public static boolean deleteDir(File _path) {
        // 1. to check whether the path exists
        if (!_path.exists()) {
            return false;
        }

        // 2. to delete the files in the path
        if (_path.isDirectory()) {
            // if _path is not a dir,files=null
            File[] files = _path.listFiles();
            File aFile;
            for (int i = 0; i < files.length; i++) {
                aFile = files[i];
                if (aFile.isDirectory()) {
                    deleteDir(aFile);
                } else {
                    aFile.delete();
                }
            }// endfor
        }

        // 3. to delete the path self
        return _path.delete();
    }

    /**
     * ��ȡĳһ·���µ����ļ���
     * 
     * @param _dir
     *            ·������
     * @return ���ļ��ж�������
     */
    public static File[] listSubDirectories(String _dir) {
        File fDir = new File(_dir);
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };

        File[] files = fDir.listFiles(fileFilter);
        return files;
    }

    // =======================================================================
    // �ļ���д��������

    // ��ȡ�ļ������ݣ������ַ������͵��ļ�����
    /**
     * ��ȡ�ļ������ݣ������ַ������͵��ļ�����
     * 
     * @param _sFileName
     *            �ļ���
     * @return
     * @throws Exception
     */
    public static String readFile(String _sFileName) throws Exception {
        // FileReader fileReader = null;
        //
        // StringBuffer buffContent = null;
        // String sLine;
        //
        // //caohui@2004-07-19 �����쳣�Ƕ�����Դ���ͷ�
        // FileInputStream fis = null;
        // BufferedReader buffReader = null;
        //
        // try {
        // //[frankwater|2002.10.23]���Ӷ�ȡ�ļ����ַ�����CMyString.FILE_WRITING_ENCODING
        // fis = new FileInputStream(_sFileName);
        // buffReader = new BufferedReader(new InputStreamReader(fis,
        // CMyString.FILE_WRITING_ENCODING));
        // //[frankwater|2002.10.23]���ζ�ȡ�ļ��е�����
        // while ((sLine = buffReader.readLine()) != null) {
        // if (buffContent == null) {
        // buffContent = new StringBuffer();
        // } else {
        // buffContent.append("\n");
        // }
        // buffContent.append(sLine);
        // }//end while
        // //[frankwater|2002.10.23]�رմ򿪵��ַ������ļ���
        //
        // //[frankwater|2002.10.23]�����ļ�������
        // return (buffContent == null ? "" : buffContent.toString());
        // } catch (FileNotFoundException ex) {
        // throw new CMyException(ExceptionNumber.ERR_FILE_NOTFOUND,
        // "Ҫ��ȡ���ļ�û���ҵ�(CMyFile.readFile)", ex);
        // } catch (IOException ex) {
        // throw new CMyException(ExceptionNumber.ERR_FILEOP_READ,
        // "���ļ�ʱ����(CMyFile.readFile)", ex);
        // } finally {
        // //�����쳣ʱ��Դ���ͷ�
        // try {
        // if (fileReader != null)
        // fileReader.close();
        // if (buffReader != null)
        // buffReader.close();
        // if (fis != null)
        // fis.close();
        // } catch (Exception ex) {
        // }
        //
        // }//end try

        // wenyh@2005-5-10 15:41:27 add comment:�޸�
        return readFile(_sFileName, CMyString.FILE_WRITING_ENCODING);
    }// END: readFile()

    // wenyh@2005-5-10 15:32:45 add comment:��ӽӿ�,ָ���ַ�������ļ�

    /**
     * ��ȡ�ļ������ݣ������ַ������͵��ļ�����
     * 
     * @param _sFileName
     *            �ļ���
     * @param _sEncoding
     *            ��ָ�����ַ������ȡ�ļ�����,Ĭ��Ϊ"UTF-8"
     * @return
     * @throws Exception
     */
    public static String readFile(String _sFileName, String _sEncoding)
            throws Exception {
        FileReader fileReader = null;

        StringBuffer buffContent = null;
        String sLine;

        // caohui@2004-07-19 �����쳣�Ƕ�����Դ���ͷ�
        FileInputStream fis = null;
        BufferedReader buffReader = null;
        if (_sEncoding == null) {
            _sEncoding = "UTF-8";
        }

        try {
            // [frankwater|2002.10.23]���Ӷ�ȡ�ļ����ַ�����CMyString.FILE_WRITING_ENCODING
            fis = new FileInputStream(_sFileName);
            buffReader = new BufferedReader(new InputStreamReader(fis,
                    _sEncoding));
            // [frankwater|2002.10.23]���ζ�ȡ�ļ��е�����
            while ((sLine = buffReader.readLine()) != null) {
                if (buffContent == null) {
                    buffContent = new StringBuffer();
                } else {
                    buffContent.append("\n");
                }
                buffContent.append(sLine);
            }// end while
            // [frankwater|2002.10.23]�رմ򿪵��ַ������ļ���

            // [frankwater|2002.10.23]�����ļ�������
            return (buffContent == null ? "" : buffContent.toString());
        } catch (FileNotFoundException ex) {
            throw new Exception("Ҫ��ȡ���ļ�û���ҵ�(CMyFile.readFile)", ex);
        } catch (IOException ex) {
            throw new Exception("���ļ�ʱ����(CMyFile.readFile)", ex);
        } finally {
            // �����쳣ʱ��Դ���ͷ�
            try {
                if (fileReader != null)
                    fileReader.close();
                if (buffReader != null)
                    buffReader.close();
                if (fis != null)
                    fis.close();
            } catch (Exception ex) {
            }

        }// end try
    }

    public static byte[] readBytesFromFile(String _sFileName) throws Exception {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(_sFileName);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream(2048);
            int nLen = 0;
            while ((nLen = fis.read(buffer)) > 0) {
                bos.write(buffer, 0, nLen);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception ignore) {
            }
            throw new Exception("��ȡ�ļ�[" + _sFileName + "]ʧ�ܣ�");
        }
    }

    // ��ָ������_sFileContent�����µ��ļ�_sFileName
    /**
     * ��ָ������_sFileContent�����µ��ļ�_sFileName
     * 
     * @param _sFileName
     *            �ļ���
     * @param _sFileContent
     *            ָ��������
     * @return
     * @throws Exception
     */
    public static boolean writeFile(String _sFileName, String _sFileContent)
            throws Exception {
        return writeFile(_sFileName, _sFileContent,
                CMyString.FILE_WRITING_ENCODING);
    }// END: writeFile()

    /**
     * ��ָ������_sFileContent�����µ��ļ�_sFileName
     * 
     * @param _sFileName
     *            �ļ���
     * @param _sFileContent
     *            ָ��������
     * @return
     * @throws Exception
     */
    public static boolean writeFile(String _sFileName, String _sFileContent,
            String _encoding) throws Exception {
        return writeFile(_sFileName, _sFileContent, _encoding, false);
    }// END: writeFile()

    public static boolean writeFile(String _sFileName, String _sFileContent,
            String _sFileEncoding, boolean _bWriteUnicodeFlag) throws Exception {
        // 1.����Ŀ¼
        String sPath = extractFilePath(_sFileName);
        if (!CMyFile.pathExists(sPath)) {
            CMyFile.makeDir(sPath, true);
        }
        String sFileEncoding = CMyString.showNull(_sFileEncoding,
                CMyString.FILE_WRITING_ENCODING);

        boolean bRet = false;
        // caohui@�����쳣�Ĵ���
        FileOutputStream fos = null;
        Writer outWriter = null;
        try {
            fos = new FileOutputStream(_sFileName);
            outWriter = new OutputStreamWriter(fos, sFileEncoding); // ָ�����뷽ʽ
            if (_bWriteUnicodeFlag)
                outWriter.write(0xFEFF);
            outWriter.write(_sFileContent); // д����

            bRet = true;
        } catch (Exception ex) {
            m_oLogger.error("д�ļ�[" + _sFileName + "]�����쳣", ex);
            throw new Exception("д�ļ�����(CMyFile.writeFile)", ex);
        } finally {
            try {
                if (outWriter != null) {
                    outWriter.flush();
                    outWriter.close();
                }
                if (fos != null)
                    fos.close();
            } catch (Exception ex) {
            }
        }
        return bRet;
    }// END: writeFile()

    // ��ָ��������_sAddContent׷�ӵ��ļ�_sFileName��
    /**
     * ��ָ��������_sAddContent׷�ӵ��ļ�_sFileName��
     * 
     * @param _sFileName
     *            �ļ���
     * @param _sAddContent
     *            ׷�ӵ�����
     * @return
     * @throws Exception
     */
    public static boolean appendFile(String _sFileName, String _sAddContent)
            throws Exception {
        boolean bResult = false;
        // caohui@2004-07-19 �ͷ���Դ
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(_sFileName, "rw");
            raf.seek(raf.length());
            raf.writeBytes(_sAddContent);
            bResult = true;
        } catch (Exception ex) {
            throw new Exception("���ļ�׷������ʱ�����쳣(CMyFile.appendFile)", ex);
        } finally {
            // caohui@2004-07-19 �ͷ���Դ
            try {
                if (raf != null)
                    raf.close();
            } catch (Exception ex) {
            }
        }
        return bResult;
    }// END: appendFile()

    /**
     * �ƶ��ļ�
     * 
     * @param _sSrcFile
     *            ���ƶ����ļ�
     * @param _sDstFile
     *            Ŀ���ļ�
     * @return
     * @throws Exception
     */
    public static boolean moveFile(String _sSrcFile, String _sDstFile)
            throws Exception {
        return moveFile(_sSrcFile, _sDstFile, true);
    }

    /**
     * �ƶ��ļ�
     * 
     * @param _sSrcFile
     *            ���ƶ����ļ�
     * @param _sDstFile
     *            Ŀ���ļ�
     * @param _bMakeDirIfNotExists
     *            ��Ŀ��·�������ڣ��Ƿ񴴽�;��ȱʡ,Ĭ��ֵΪtrue.
     * @return
     * @throws Exception
     */
    public static boolean moveFile(String _sSrcFile, String _sDstFile,
            boolean _bMakeDirIfNotExists) throws Exception {
        // 1.����
        copyFile(_sSrcFile, _sDstFile, _bMakeDirIfNotExists);
        // 2.ɾ��
        deleteFile(_sSrcFile);
        return false;
    }

    /**
     * �����ļ�
     * 
     * @param _sSrcFile
     *            Դ�ļ����������·����
     * @param _sDstFile
     *            Ŀ���ļ����������·����
     * @param _bMakeDirIfNotExists
     *            ��Ŀ��·�������ڣ��Ƿ񴴽�;��ȱʡ,Ĭ��ֵΪtrue.
     * @return ���ļ����Ƴɹ����򷵻�true�����򣬷���false.
     * @throws Exception
     *             Դ�ļ�������,��Ŀ���ļ�����Ŀ¼������,���ļ�����ʧ��,���׳��쳣.
     */
    public static boolean copyFile(String _sSrcFile, String _sDstFile)
            throws Exception {
        return copyFile(_sSrcFile, _sDstFile, true);
    }

    public static boolean copyFile(String _sSrcFile, String _sDstFile,
            boolean _bMakeDirIfNotExists) throws Exception {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(_sSrcFile); // ���ļ�������,���׳��쳣

            // why@2003-09-27 ���Ŀ¼�����ڣ��򴴽�Ŀ¼
            try {
                fos = new FileOutputStream(_sDstFile);
            } catch (FileNotFoundException ex) {
                if (_bMakeDirIfNotExists) { // �Զ�����Ŀ¼
                    if (!CMyFile.makeDir(CMyFile.extractFilePath(_sDstFile),
                            true)) {
                        throw new Exception("ΪĿ���ļ�[" + _sDstFile + "]����Ŀ¼ʧ�ܣ�");
                    }
                    fos = new FileOutputStream(_sDstFile);
                } else {
                    throw new Exception("ָ��Ŀ���ļ�[" + _sDstFile + "]����Ŀ¼�����ڣ�", ex);
                }
            }// end try

            byte buffer[] = new byte[4096];
            int bytes;
            while ((bytes = fis.read(buffer, 0, 4096)) > 0) {
                fos.write(buffer, 0, bytes);
            }// end while
        } catch (FileNotFoundException ex) {
            throw new Exception("Ҫ���Ƶ�ԭ�ļ�û�з���(CMyFile.copyFile)", ex);
        } catch (IOException ex) {
            throw new Exception("�����ļ�ʱ�����쳣(CMyFile.copyFile)", ex);
        } finally {
            if (fos != null)
                try {
                    fos.close();
                } catch (Exception ex) {
                }
            if (fis != null)
                try {
                    fis.close();
                } catch (Exception ex) {
                }
        }// end try

        return true;
    }// END: copyFile()

    /**
     * map the resource related path to full real path
     * 
     * @param _resource
     *            related path of resource
     * @return the full real path
     * @throws Exception
     *             if encounter errors
     */
    public static String mapResouceFullPath(String _resource) throws Exception {
        // URL url = CMyFile.class.getResource(_resource);
        URL url = Thread.currentThread().getContextClassLoader().getResource(
                _resource);
        if (url == null) {
            throw new Exception("�ļ�[" + _resource + "]û���ҵ���");
        }

        // else
        String sPath = null;
        try {
            sPath = url.getFile();
            if (sPath.indexOf('%') >= 0) {
                // ge modify by gfc @2007-8-23 13:19:30 ����enc�������������ʱ�׿�ָ���쳣
                // sPath = URLDecoder.decode(url.getFile(), null);
                String enc = System.getProperty("file.encoding", "GBK");
                sPath = URLDecoder.decode(url.getFile(), enc);

            }
        } catch (Exception ex) {
            throw new Exception("�ļ�[" + url.getFile() + "]ת��ʧ�ܣ�", ex);
        }
        return sPath;
    }

    public static String mapResouceFullPath(String _resource, Class _currClass)
            throws Exception {
        URL url = _currClass.getResource(_resource);
        if (url == null) {
            throw new Exception("�ļ�[" + _resource + "]û���ҵ���");
        }

        String sPath = null;
        try {
            sPath = url.getFile();
            if (sPath.indexOf('%') >= 0) {
                // ge modify by gfc @2007-8-23 13:19:30 ����enc�������������ʱ�׿�ָ���쳣
                // sPath = URLDecoder.decode(url.getFile(), null);
                String enc = System.getProperty("file.encoding", "GBK");
                sPath = URLDecoder.decode(url.getFile(), enc);

            }
        } catch (Exception ex) {
            throw new Exception("�ļ�[" + url.getFile() + "]ת��ʧ�ܣ�", ex);
        }
        return sPath;
    }

    // ==============================================================
    // ����

    public static void main(String args[]) {
        try {
            CMyFile.writeFile("c:\\test.txt", "�й���test", "UTF-16LE", true);

            String sSrcFile = "";
            // String sDstFile = "";
            long lStartTime, lEndTime;
            // �����ļ��ĸ��ƣ�
            sSrcFile = "d:\\temp\\InfoRadar.pdf";
            // sDstFile = "d:\\temp\\sub\\InfoRadar_old.pdf";
            lStartTime = System.currentTimeMillis();
            // CMyFile.copyFile(sSrcFile, sDstFile);
            lEndTime = System.currentTimeMillis();
            System.out.println("==============����ʱ�䣺" + (lEndTime - lStartTime)
                    + "ms ==============");

            sSrcFile = "d:\\write_test.html";
            String sContent = CMyFile.readFile(sSrcFile);
            lStartTime = System.currentTimeMillis();
            CMyFile.writeFile(sSrcFile + ".new", sContent);
            lEndTime = System.currentTimeMillis();
            System.out.println("==============����ʱ�䣺" + (lEndTime - lStartTime)
                    + "ms ==============");

            /*
             * //CMyFile.deleteDir("d:\\trswcm\\wcm\\doc\\temp\\",2); CMyFile wf =
             * new CMyFile(); String sFilePathName[] =
             * {"d:\\CMyFileOut.txt","CMyFileOut.txt","d:\\test\\CMyFileOut","\\\\wanghaiyang\\share\\test.txt"};
             * int i;
             * 
             * //�����й��ļ���Ŀ¼��顢������ɾ���Ȳ��� String sPath = "d:\\test2\\test21\\";
             * String sSubPath = sPath + "test211\\"; boolean bRet;
             * System.out.println( sPath + "=" + CMyFile.fileExists(sPath) );
             * 
             * bRet = CMyFile.makeDir(sPath,true); System.out.println("Create
             * dir["+sPath+"]=" +bRet ); System.out.println( sPath + "=" +
             * CMyFile.fileExists(sPath) );
             * 
             * bRet = CMyFile.makeDir(sSubPath,true); System.out.println("Create
             * dir["+sSubPath+"]=" +bRet ); System.out.println( sSubPath+ "=" +
             * CMyFile.fileExists(sSubPath) );
             * 
             * bRet = CMyFile.deleteDir( sPath, true );
             * System.out.println("Delete dir=" + bRet ); System.out.println(
             * sPath + CMyFile.fileExists(sPath) );
             * 
             * //�����й��ļ�����ȡ�Ⱥ��� for( i=0; i <sFilePathName.length; i++ ){
             * System.out.println("FilePathName=["+sFilePathName[i]+"]");
             * System.out.println(" File
             * found="+CMyFile.fileExists(sFilePathName[i]) );
             * System.out.println(" FileName=[" +
             * CMyFile.extractFileName(sFilePathName[i]) + "]");
             * System.out.println(" FileExt=[" +
             * CMyFile.extractFileExt(sFilePathName[i]) + "]");
             * System.out.println(" FilePath=[" +
             * CMyFile.extractFilePath(sFilePathName[i]) + "]");
             * System.out.println(" FileAbsolutePathName=[" +
             * CMyFile.toAbsolutePathName(sFilePathName[i]) + "]");
             * System.out.println(" FileDrive=[" +
             * CMyFile.extractFileDrive(sFilePathName[i]) + "]"); }//end for
             * 
             * 
             * //��strContentд�뵽�ļ�strFilename�� String strContent = "This is a
             * test file."; wf.writeFile("d:\\CMyFileOut.txt", strContent);
             * //Ҫ���ļ�����ǰĿ¼�±����д��ļ��� ���磺template.html System.out.println(
             * wf.readFile("template.html") );
             */
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }// end try
    }

}