/*
 * History			Who			What
 * 2006-03-15		wenyh		����splitToInt(String,String)����:
 * 									�����һ������Ϊ��,���س���Ϊ0������,����ڶ���Ϊ��,��Ĭ��ʹ��','�ָ�
 * 2007.20.28		wenyh		����setStrEndWith���߼�,����StringIndexOutOfBoundsException����.
 * 
 */

package com.trs.infra.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Created: 2001.5
 * </p>
 * <p>
 * Last Modified: 2002.05.25/2002.4.27
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * class CMyString ���� �ַ����������
 * </p>
 */

/**
 * <p>
 * Title: TRS ����Э��ƽ̨��TRS WCM��
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * class CMyString ���� �ַ����������
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001
 * </p>
 * <p>
 * Company: www.trs.com.cn
 * </p>
 * 
 * @author TRS��Ϣ�������޹�˾
 * @version 1.0
 */

public class CMyString {

    /** Ĭ���ַ����뼯 */
    public static String ENCODING_DEFAULT = "UTF-8";

    public static String GET_ENCODING_DEFAULT = "UTF-8";

    public static String FILE_WRITING_ENCODING = "GBK";

    public CMyString() {
        super();
    }

    // ==============================================================================
    // �����ַ�������

    /**
     * �ж�ָ���ַ����Ƿ�Ϊ��
     * 
     * @param _string
     *            ָ�����ַ���
     * @return ���ַ���Ϊ�ն���_string==null����մ�������Ϊ0�����򷵻�true�����򣬷���false.
     */
    public static boolean isEmpty(String _string) {
        return ((_string == null) || (_string.trim().length() == 0));
    }

    /**
     * �ж�ָ���ַ����Ƿ�Ϊ��
     * 
     * @param _string
     *            ָ�����ַ���
     * @return ���ַ���Ϊ�ն���_string==null����մ�������Ϊ0�����򷵻�true�����򣬷���false.
     * @deprecated �ɺ��� isEmpty �滻
     * @see isEmpty( String _string )
     */
    public static boolean isEmptyStr(String _string) {
        return ((_string == null) || (_string.trim().length() == 0));
    }

    /**
     * �ַ�����ʾ����������Ϊ�ն����򷵻�ָ�����ַ���
     * 
     * @see showNull( String _sValue, String _sReplaceIfNull )
     */
    public static String showObjNull(Object p_sValue) {
        return showObjNull(p_sValue, "");
    }

    /**
     * �ַ�����ʾ����������Ϊ�ն����򷵻�ָ�����ַ���
     * 
     * @param _sValue
     *            ָ�����ַ���
     * @param _sReplaceIfNull
     *            ��_sValue==nullʱ���滻��ʾ�ַ�������ѡ������ȱʡֵΪ���ַ�����""��
     * @return �������ַ���
     */
    public static String showObjNull(Object _sValue, String _sReplaceIfNull) {
        if (_sValue == null)
            return _sReplaceIfNull;
        return _sValue.toString();
    }

    /**
     * �ַ�����ʾ����������Ϊ�ն����򷵻�ָ�����ַ���
     * 
     * @see showNull( String _sValue, String _sReplaceIfNull )
     */
    public static String showNull(String p_sValue) {
        return showNull(p_sValue, "");
    }

    /**
     * �ַ�����ʾ����������Ϊ�ն����򷵻�ָ�����ַ���
     * 
     * @param _sValue
     *            ָ�����ַ���
     * @param _sReplaceIfNull
     *            ��_sValue==nullʱ���滻��ʾ�ַ�������ѡ������ȱʡֵΪ���ַ�����""��
     * @return �������ַ���
     */
    public static String showNull(String _sValue, String _sReplaceIfNull) {
        return (_sValue == null ? _sReplaceIfNull : _sValue);
    }

    /**
     * ��չ�ַ������ȣ������Ȳ��㣬������ָ�����ַ������
     * 
     * @param _string
     *            Ҫ��չ���ַ���
     * @param _length
     *            ��չ����ַ������ȡ�
     * @param _chrFill
     *            ��չʱ�����������ַ���
     * @param _bFillOnLeft
     *            ��չʱ���Ƿ�Ϊ����䣨��չ��������Ϊ�����
     * @return ������չ����ַ���
     */
    public static String expandStr(String _string, int _length, char _chrFill,
            boolean _bFillOnLeft) {
        int nLen = _string.length();
        if (_length <= nLen)
            return _string; // �����ѹ�

        // else,��չ�ַ�������
        String sRet = _string;
        for (int i = 0; i < _length - nLen; i++) {
            sRet = (_bFillOnLeft ? _chrFill + sRet : sRet + _chrFill); // ���
        }
        return sRet;
    }

    /**
     * �����ַ������һλΪָ�����ַ�
     * 
     * @param _string
     *            ָ�����ַ���
     * @param _chrEnd
     *            ָ���ַ������ַ������һλ���Ǹ��ַ��������ַ���β��׷�Ӹ��ַ�
     * @return �������ַ��� ���<code>isEmpty(_string)</code>����true,��ԭ������
     * @see #isEmpty(String)
     */
    public static String setStrEndWith(String _string, char _chrEnd) {
        // if (_string == null)
        // return null;
        // if (_string.charAt(_string.length() - 1) != _chrEnd)
        // return _string + _chrEnd;
        // // else
        // return _string;

        // wenyh@2007-2-28 11:08:58 add comment:
        // the above code will be throw an StringIndexOutOfBoundsException when
        // the _string.length<=0
        return setStrEndWith0(_string, _chrEnd);
    }

    private static String setStrEndWith0(String _str, char _charEnd) {
        if (isEmpty(_str) || _str.endsWith(String.valueOf(_charEnd))) {
            return _str;
        }

        return _str + _charEnd;
    }

    /**
     * ����ָ�����ȵĿո��ַ���
     * 
     * @param _length
     *            ָ������
     * @return ָ�����ȵĿո��ַ���
     */
    public static String makeBlanks(int _length) {
        if (_length < 1)
            return "";
        StringBuffer buffer = new StringBuffer(_length);
        for (int i = 0; i < _length; i++) {
            buffer.append(' ');
        }
        return buffer.toString();
    }

    // =============================================================================
    // �ַ����滻

    /**
     * �ַ����滻���������ڽ�ָ���ַ�����ָ�����ַ����滻Ϊ�µ��ַ�����
     * 
     * @param _strSrc
     *            Դ�ַ�����
     * @param _strOld
     *            ���滻�ľ��ַ���
     * @param _strNew
     *            �����滻���ַ��������ַ���
     * @return �滻�������ַ���
     */
    public static String replaceStr(String _strSrc, String _strOld,
            String _strNew) {
        if (_strSrc == null || _strNew == null || _strOld == null)
            return _strSrc;

        // ��ȡԴ�ַ�����Ӧ���ַ�����
        char[] srcBuff = _strSrc.toCharArray();
        int nSrcLen = srcBuff.length;
        if (nSrcLen == 0)
            return "";

        // ��ȡ���ַ�����Ӧ���ַ�����
        char[] oldStrBuff = _strOld.toCharArray();
        int nOldStrLen = oldStrBuff.length;
        if (nOldStrLen == 0 || nOldStrLen > nSrcLen)
            return _strSrc;

        StringBuffer retBuff = new StringBuffer((nSrcLen * (1 + _strNew
                .length()
                / nOldStrLen)));

        int i, j, nSkipTo;
        boolean bIsFound = false;

        i = 0;
        while (i < nSrcLen) {
            bIsFound = false;

            // �ж��Ƿ�����Ҫ�ҵ��ַ���
            if (srcBuff[i] == oldStrBuff[0]) {
                for (j = 1; j < nOldStrLen; j++) {
                    if (i + j >= nSrcLen)
                        break;
                    if (srcBuff[i + j] != oldStrBuff[j])
                        break;
                }
                bIsFound = (j == nOldStrLen);
            }

            // ���ҵ����滻����������
            if (bIsFound) { // �ҵ�
                retBuff.append(_strNew);
                i += nOldStrLen;
            } else { // û���ҵ�
                if (i + nOldStrLen >= nSrcLen) {
                    nSkipTo = nSrcLen - 1;
                } else {
                    nSkipTo = i;
                }
                for (; i <= nSkipTo; i++) {
                    retBuff.append(srcBuff[i]);
                }
            }
        }// end while
        srcBuff = null;
        oldStrBuff = null;
        return retBuff.toString();
    }

    /**
     * �ַ����滻���������ڽ�ָ���ַ�����ָ�����ַ����滻Ϊ�µ��ַ�����
     * 
     * @param _strSrc
     *            Դ�ַ�����
     * @param _strOld
     *            ���滻�ľ��ַ���
     * @param _strNew
     *            �����滻���ַ��������ַ���
     * @return �滻�������ַ���
     */
    public static String replaceStr(StringBuffer _strSrc, String _strOld,
            String _strNew) {
        if (_strSrc == null)
            return null;

        // ��ȡԴ�ַ�����Ӧ���ַ�����
        int nSrcLen = _strSrc.length();
        if (nSrcLen == 0)
            return "";

        // ��ȡ���ַ�����Ӧ���ַ�����
        char[] oldStrBuff = _strOld.toCharArray();
        int nOldStrLen = oldStrBuff.length;
        if (nOldStrLen == 0 || nOldStrLen > nSrcLen)
            return _strSrc.toString();

        StringBuffer retBuff = new StringBuffer((nSrcLen * (1 + _strNew
                .length()
                / nOldStrLen)));

        int i, j, nSkipTo;
        boolean bIsFound = false;

        i = 0;
        while (i < nSrcLen) {
            bIsFound = false;

            // �ж��Ƿ�����Ҫ�ҵ��ַ���
            if (_strSrc.charAt(i) == oldStrBuff[0]) {
                for (j = 1; j < nOldStrLen; j++) {
                    if (i + j >= nSrcLen)
                        break;
                    if (_strSrc.charAt(i + j) != oldStrBuff[j])
                        break;
                }
                bIsFound = (j == nOldStrLen);
            }

            // ���ҵ����滻����������
            if (bIsFound) { // �ҵ�
                retBuff.append(_strNew);
                i += nOldStrLen;
            } else { // û���ҵ�
                if (i + nOldStrLen >= nSrcLen) {
                    nSkipTo = nSrcLen - 1;
                } else {
                    nSkipTo = i;
                }
                for (; i <= nSkipTo; i++) {
                    retBuff.append(_strSrc.charAt(i));
                }
            }
        }// end while
        oldStrBuff = null;
        return retBuff.toString();
    }

    // ==============================================================================
    // �ַ����봦����

    /**
     * �ַ�������ת�����������ڽ�ָ��������ַ���ת��Ϊ��׼��Unicode���ַ���
     * 
     * @see getStr( String _strSrc, String _encoding )
     */
    public static String getStr(String _strSrc) {
        return getStr(_strSrc, ENCODING_DEFAULT);
    }

    /**
     * �ַ�ת��������������������
     * 
     * @param _strSrc
     *            Դ�ַ���
     * @param _bPostMethod
     *            �ύ���ݵķ�ʽ��Get��ʽ����GET_ENCODING_DEFAULT�ַ�����Post��ʽ����ENCODING_DEFAULT�ַ�����
     * @return
     */
    public static String getStr(String _strSrc, boolean _bPostMethod) {
        return getStr(_strSrc, (_bPostMethod ? ENCODING_DEFAULT
                : GET_ENCODING_DEFAULT));
    }

    /**
     * �ַ�������ת�����������ڽ�ָ��������ַ���ת��Ϊ��׼��Unicode���ַ���
     * <p>
     * Purpose: ת���ַ������룬���ڽ��������ʾ����
     * </p>
     * <p>
     * Usage�� ��ҳ���л�ʱ����ȡ����ʾ�����ַ�������ʱ���á�
     * </p>
     * 
     * @param _strSrc
     *            ��Ҫת�����ַ���
     * @param _encoding
     *            ָ���ַ�����_strSrc���ı��뷽ʽ����ѡ������ȱʡֵΪENCODING_DEFAULT
     * @return
     */
    public static String getStr(String _strSrc, String _encoding) {
        if (_encoding == null || _encoding.length() == 0)
            return _strSrc;

        try {
            byte[] byteStr = new byte[_strSrc.length()];
            char[] charStr = _strSrc.toCharArray();
            for (int i = byteStr.length - 1; i >= 0; i--) {
                byteStr[i] = (byte) charStr[i];
            }
            /*
             * ���ϵ�ʵ�ֺ�����ķ������õ�ʵ���ǵȼ۵ģ�ͬ���ض�����16λ�ַ��ĸ�8λ�� _strSrc.getBytes(0,
             * _strSrc.length(), byteStr, 0);
             * ֮��������д��������String���͵ķ������ã����ϣ�����Ҫ��ȷ���ֶ�����8λ��Ϊ��
             */
            return new String(byteStr, _encoding);
            // return new String(_strSrc.getBytes(), _encoding);
            // commented by frank:2002-09-13
            // byte[] bytes = _strSrc.getBytes( _encoding ); //why@2002-04-22
            // ʹ��ָ���ַ�����
            // return new String( bytes );
        } catch (Exception ex) {
            return _strSrc; // ����ʱ������Դ�ַ��� //why@2002-04-27��������"null"
        }
    }// END: getStr()

    /**
     * ��ָ�����ַ���ת��ΪISO-8859-1������ַ���
     * 
     * @param _strSrc
     *            ָ����Դ�ַ���
     * @return ת������ַ���
     */
    public static String toISO_8859(String _strSrc) {
        if (_strSrc == null)
            return null;

        try {
            return new String(_strSrc.getBytes(), "ISO-8859-1");
        } catch (Exception ex) {
            return _strSrc;
        }
    }

    /**
     * ��ָ�����ַ���ת��ΪISO-8859-1������ַ���
     * 
     * @param _strSrc
     *            ָ����Դ�ַ���
     * @return ת������ַ���
     * @deprecated ����ģ�����Ѿ�ʹ��toISO_8859�滻
     */
    public static String toUnicode(String _strSrc) {
        return toISO_8859(_strSrc);
    }

    // why@2002-04-27 come from java.util.ZipOutputSteam
    /**
     * ��ȡ�ַ���UTF8������ֽ���
     * <p>
     * ˵�����ȼ��� <code>_string.getBytes("UTF8")</code>
     * </p>
     * 
     * @param _string
     *            Դ�ַ���
     * @return UTF8������ֽ�����
     */
    public static byte[] getUTF8Bytes(String _string) {
        char[] c = _string.toCharArray();
        int len = c.length;

        // Count the number of encoded bytes...
        int count = 0;
        for (int i = 0; i < len; i++) {
            int ch = c[i];
            if (ch <= 0x7f) {
                count++;
            } else if (ch <= 0x7ff) {
                count += 2;
            } else {
                count += 3;
            }
        }

        // Now return the encoded bytes...
        byte[] b = new byte[count];
        int off = 0;
        for (int i = 0; i < len; i++) {
            int ch = c[i];
            if (ch <= 0x7f) {
                b[off++] = (byte) ch;
            } else if (ch <= 0x7ff) {
                b[off++] = (byte) ((ch >> 6) | 0xc0);
                b[off++] = (byte) ((ch & 0x3f) | 0x80);
            } else {
                b[off++] = (byte) ((ch >> 12) | 0xe0);
                b[off++] = (byte) (((ch >> 6) & 0x3f) | 0x80);
                b[off++] = (byte) ((ch & 0x3f) | 0x80);
            }
        }
        return b;
    }

    // why@2002-04-27 come from java.util.ZipInputStream
    public static String getUTF8String(byte[] b) {
        return getUTF8String(b, 0, b.length);
    }

    /**
     * ��ָ�����ֽ���������ȡUTF8������ַ���
     * <p>
     * ˵���������ȼ��� <code> new String(b,"UTF8") </code>
     * </p>
     * 
     * @param b
     *            ָ�����ֽ����飨UTF8���룩
     * @param off
     *            ��ʼ��ȡ���ֽ���ʼλ�ã���ѡ������ȱʡֵΪ0��
     * @param len
     *            ��ȡ���ֽ�������ѡ���飬ȱʡֵΪȫ����
     * @return ��ȡ��õ����ַ�����
     */
    public static String getUTF8String(byte[] b, int off, int len) {
        // First, count the number of characters in the sequence
        int count = 0;
        int max = off + len;
        int i = off;
        while (i < max) {
            int c = b[i++] & 0xff;
            switch (c >> 4) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                // 0xxxxxxx
                count++;
                break;
            case 12:
            case 13:
                // 110xxxxx 10xxxxxx
                if ((b[i++] & 0xc0) != 0x80) {
                    throw new IllegalArgumentException();
                }
                count++;
                break;
            case 14:
                // 1110xxxx 10xxxxxx 10xxxxxx
                if (((b[i++] & 0xc0) != 0x80) || ((b[i++] & 0xc0) != 0x80)) {
                    throw new IllegalArgumentException();
                }
                count++;
                break;
            default:
                // 10xxxxxx, 1111xxxx
                throw new IllegalArgumentException();
            }
        }
        if (i != max) {
            throw new IllegalArgumentException();
        }
        // Now decode the characters...
        char[] cs = new char[count];
        i = 0;
        while (off < max) {
            int c = b[off++] & 0xff;
            switch (c >> 4) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                // 0xxxxxxx
                cs[i++] = (char) c;
                break;
            case 12:
            case 13:
                // 110xxxxx 10xxxxxx
                cs[i++] = (char) (((c & 0x1f) << 6) | (b[off++] & 0x3f));
                break;
            case 14:
                // 1110xxxx 10xxxxxx 10xxxxxx
                int t = (b[off++] & 0x3f) << 6;
                cs[i++] = (char) (((c & 0x0f) << 12) | t | (b[off++] & 0x3f));
                break;
            default:
                // 10xxxxxx, 1111xxxx
                throw new IllegalArgumentException();
            }
        }
        return new String(cs, 0, count);
    }

    // ==============================================================================
    // �ַ�����ʾ������

    /**
     * ���ֽ��������Ϊ16��������ʾ���ַ���
     * 
     * @see byteToHexString( byte[] _bytes, char _delim )
     */
    public static String byteToHexString(byte[] _bytes) {
        return byteToHexString(_bytes, ',');
    }

    /**
     * ���ֽ��������Ϊ16�����޷�������ʾ���ַ���
     * 
     * @param _bytes
     *            �ֽ�����
     * @param _delim
     *            �ֽ�������ʾʱ���ֽ�֮��ķָ�������ѡ������ȱʡֵΪ','
     * @return 16�����޷�������ʾ���ֽ�����
     */
    public static String byteToHexString(byte[] _bytes, char _delim) {
        String sRet = "";
        for (int i = 0; i < _bytes.length; i++) {
            if (i > 0) {
                sRet += _delim;
            }
            sRet += Integer.toHexString(_bytes[i]);
        }
        return sRet;
    }

    /**
     * ���ֽ��������Ϊָ����������ʾ���ַ�����ע�⣺�������и��ţ�
     * 
     * @param _bytes
     *            �ֽ�����
     * @param _delim
     *            �ֽ�������ʾʱ���ֽ�֮��ķָ�������ѡ������ȱʡֵΪ','
     * @param _radix
     *            ����������16���ƣ�
     * @return ָ����������ʾ���ֽ����ݣ��������ɸ��ţ�
     */
    public static String byteToString(byte[] _bytes, char _delim, int _radix) {
        String sRet = "";
        for (int i = 0; i < _bytes.length; i++) {
            if (i > 0) {
                sRet += _delim;
            }
            sRet += Integer.toString(_bytes[i], _radix);
        }
        return sRet;
    }

    /**
     * ������Html����ʾ�ı�����
     * 
     * @see <code>transDisplay( String _sContent, boolean _bChangeBlank )</code>
     */
    public static String transDisplay(String _sContent) {
        return transDisplay(_sContent, true);
    }

    /**
     * ������Html����ʾ�ı����ݡ����ո��ת��Ϊhtml��ǡ�
     * <p>
     * ˵������������ʱ����ʹ�� <code>style="WORD_WRAP:keepall"</code> �����ܽ��ո�ת��Ϊ
     * <code>&amp;nbsp;</code>
     * </p>
     * 
     * @param _sContent
     *            Ҫ��ʾ������
     * @param _bChangeBlank
     *            �Ƿ�ת���ո������ѡ������Ĭ��ֵΪtrue.
     * @return ת�����Html�ı�
     */
    public static String transDisplay(String _sContent, boolean _bChangeBlank) {
        if (_sContent == null)
            return "";

        char[] srcBuff = _sContent.toCharArray();
        int nSrcLen = srcBuff.length;

        StringBuffer retBuff = new StringBuffer(nSrcLen * 2);

        int i;
        char cTemp;
        for (i = 0; i < nSrcLen; i++) {
            cTemp = srcBuff[i];
            switch (cTemp) {
            case ' ':
                retBuff.append(_bChangeBlank ? "&nbsp;" : " ");
                break;
            case '<':
                retBuff.append("&lt;");
                break;
            case '>':
                retBuff.append("&gt;");
                break;
            case '\n':
                // �ٴ�����׺Ͷ�β��ʱ����
                if (_bChangeBlank)
                    retBuff.append("<br>");
                break;
            case '"':
                retBuff.append("&quot;");
                break;
            case '&': // why: 2002-3-19
                // caohui@0515
                // ����unicode����
                boolean bUnicode = false;
                for (int j = (i + 1); j < nSrcLen && !bUnicode; j++) {
                    cTemp = srcBuff[j];
                    if (cTemp == '#' || cTemp == ';') {
                        retBuff.append("&");
                        bUnicode = true;
                    }
                }
                if (!bUnicode)
                    retBuff.append("&amp;");
                break;
            case 9: // Tab
                retBuff.append(_bChangeBlank ? "&nbsp;&nbsp;&nbsp;&nbsp;"
                        : "    ");
                break;

            default:
                retBuff.append(cTemp);
            }// case
        }

        // ����滻�˿ո�ֱ�ӷ��أ�������Ҫ
        if (_bChangeBlank)
            return retBuff.toString();

        // ��Ҫ���⴦����׺Ͷ�β
        return replaceStartEndSpaces(retBuff.toString());

    }// END:transDisplay

    // ��ָ���ı����ݣ���ʽ��Ϊbbs����Html�ı��ַ�����
    // ������p_strContent��p_sQuoteColor��
    // _bChangeBlank
    //
    public static String transDisplay_bbs(String _sContent, String p_sQuoteColor) {
        return transDisplay_bbs(_sContent, p_sQuoteColor, true);
    }

    /**
     * ��ָ���ı����ݣ���ʽ��Ϊbbs����Html�ı��ַ�����
     * <p>
     * ˵����[1]��������ʱ����ʹ�� style="WORD_WRAP:keepall"�����ܽ��ո�ת��Ϊ
     * <code>&amp;nbsp;</code>
     * </p>
     * <p>
     * [2]�ú�����ʽ��ʱ���������ĳһ����":"��ʼ������Ϊ��������(quote)��
     * </p>
     * <p ���ò���p_sQuoteColorָ������ɫ��ʾ��
     * </p>
     * 
     * @param _sContent
     *            �ı����ݣ�
     * @param p_sQuoteColor
     *            ���������ʾ��ɫ��
     * @param _bChangeBlank
     *            �Ƿ�ת���ո������ʡ�ԣ�Ĭ��ֵΪtrue
     * @return ת�����Html�ı�
     */
    public static String transDisplay_bbs(String _sContent,
            String p_sQuoteColor, boolean _bChangeBlank) {
        if (_sContent == null)
            return "";

        int i;
        char cTemp;
        boolean bIsQuote = false; // �Ƿ���������
        boolean bIsNewLine = true; // �Ƿ����µ�һ��

        char[] srcBuff = _sContent.toCharArray();
        int nSrcLen = srcBuff.length;

        StringBuffer retBuff = new StringBuffer((int) (nSrcLen * 1.8));

        for (i = 0; i < nSrcLen; i++) {
            cTemp = srcBuff[i];
            switch (cTemp) {
            case ':': {
                if (bIsNewLine) {
                    bIsQuote = true;
                    retBuff.append("<font color=" + p_sQuoteColor + ">:");
                } else {
                    retBuff.append(":");
                }
                bIsNewLine = false;
                break;
            }

            case ' ': {
                retBuff.append(_bChangeBlank ? "&nbsp;" : " ");
                bIsNewLine = false;
                break;
            }
            case '<': {
                retBuff.append("&lt;");
                bIsNewLine = false;
                break;
            }
            case '>': {
                retBuff.append("&gt;");
                bIsNewLine = false;
                break;
            }
            case '"': {
                retBuff.append("&quot;");
                bIsNewLine = false;
                break;
            }
            case '&': { // why: 2002-3-19
                retBuff.append("&amp;");
                bIsNewLine = false;
                break;
            }

            case 9: {// Tab
                retBuff.append(_bChangeBlank ? "&nbsp;&nbsp;&nbsp;&nbsp;"
                        : "    ");
                bIsNewLine = false;
                break;
            }

            case '\n': {
                if (bIsQuote) {
                    bIsQuote = false;
                    retBuff.append("</font>");
                }
                retBuff.append("<br>");
                bIsNewLine = true;
                break;
            }
            default: {
                retBuff.append(cTemp);
                bIsNewLine = false;
            }
            }// end case
        }// end for
        if (bIsQuote) {
            retBuff.append("</font>");
        }
        return retBuff.toString();
    }// END: transDisplay_bbs

    /**
     * javascript��ʾ�������ڴ���javascript�е��ı��ַ�����ʾ
     * 
     * @param _sContent
     *            javascript�ı�
     * @return ������javascript�ı�
     */
    public static String transJsDisplay(String _sContent) {
        if (_sContent == null)
            return "";

        char[] srcBuff = _sContent.toCharArray();
        int nSrcLen = srcBuff.length;

        StringBuffer retBuff = new StringBuffer((int) (nSrcLen * 1.5));

        int i;
        char cTemp;
        for (i = 0; i < nSrcLen; i++) {
            cTemp = srcBuff[i];
            switch (cTemp) {
            case '<':
                retBuff.append("&lt;");
                break;
            case '>':
                retBuff.append("&gt;");
                break;
            case 34: // "
                retBuff.append("&quot;");
                break;
            default:
                retBuff.append(cTemp);
            }// case
        }
        return retBuff.toString();
    }// END:transJsDisplay

    /**
     * �ַ�����������ʾ����ָ�������빹����ָ���ַ�����ͬ���ȵ��ַ���
     * <p>
     * ���ڣ�������ʾ����Ҫ���봦��ĳ���
     * </p>
     * 
     * @param _strSrc
     *            Դ�ַ���
     * @param p_chrMark
     *            ָ��������
     * @return �����봦�����ַ���
     */
    public static String transDisplayMark(String _strSrc, char p_chrMark) {
        if (_strSrc == null)
            return "";

        // else
        char[] buff = new char[_strSrc.length()];
        for (int i = 0; i < buff.length; i++) {
            buff[i] = p_chrMark;
        }
        return new String(buff);
    }

    // ==============================================================================
    // �ַ������˺���

    /**
     * SQL��������ַ����˴�����
     * <p>
     * ���ڣ�����SQL���ʱ������ַ�������ʱʹ��
     * </p>
     * <p>
     * �磺
     * <code>String strSQL = "select * from tbName where Name='"+CMyString.filterForSQL("a'bc")+"'" </code>
     * </p>
     * <p>
     * ˵������Ҫ����������ַ�����Ӧת�������磺 <code> ' ---&gt;''</code>
     * </p>
     * <p>
     * ������ʹ�õ������ַ��� <code> !@#$%^&*()+|-=\\;:\",./&lt;&gt;? </code>
     * </p>
     * 
     * @param _sContent
     *            ��Ҫ������ַ���
     * @return ���˴������ַ���
     */
    public static String filterForSQL(String _sContent) {
        if (_sContent == null)
            return "";

        int nLen = _sContent.length();
        if (nLen == 0)
            return "";

        char[] srcBuff = _sContent.toCharArray();
        StringBuffer retBuff = new StringBuffer((int) (nLen * 1.5));

        // caohui@0508 ����Ӧ�ö���Ҫ��ȥ�������ַ������޸�
        for (int i = 0; i < nLen; i++) {
            char cTemp = srcBuff[i];
            switch (cTemp) {
            case '\'': {
                retBuff.append("''");
                break;
            }
            case ';':// caohui@0516Ϊ�˲�ѯUnicode�ַ�
                boolean bSkip = false;
                for (int j = (i + 1); j < nLen && !bSkip; j++) {
                    char cTemp2 = srcBuff[j];
                    if (cTemp2 == ' ')
                        continue;
                    if (cTemp2 == '&')
                        retBuff.append(';');
                    bSkip = true;
                }
                if (!bSkip)
                    retBuff.append(';');
                break;
            // case '[': //niuzhao@2005-08-11 ����SQL Server�е�ͨ��� []
            // retBuff.append("[[]");
            // break;
            // case '_': //niuzhao@2005-08-11 ����SQL Server�е�ͨ��� _
            // retBuff.append("[_]");
            // break;
            default:
                retBuff.append(cTemp);
            }// case
        }// end for
        /*
         * for( int i=0; i <nLen; i++ ){ char cTemp = srcBuff[i]; switch( cTemp ){
         * case '\'':{ retBuff.append( "''" ); break; } case '!': case '@': case
         * '#': case '$': case '%': case '^': case '&': case '*': case '(': case
         * ')': case '+': case '|': case '-': case '=': case '\\': case ';':
         * case ':': case '\"': case ',': case '.': case '/': case ' <': case
         * '>': case '?': break; //skip default : retBuff.append( cTemp );
         * }//case }//end for
         */

        return retBuff.toString();
    }

    /**
     * XML�ı����˴��������� <code> & &lt; &gt;\ </code> �������ַ���ת������
     * 
     * @param _sContent
     *            ָ����XML�ı�����
     * @return �������ı�����
     */
    public static String filterForXML(String _sContent) {
        if (_sContent == null)
            return "";

        char[] srcBuff = _sContent.toCharArray();
        int nLen = srcBuff.length;
        if (nLen == 0)
            return "";

        StringBuffer retBuff = new StringBuffer((int) (nLen * 1.8));

        for (int i = 0; i < nLen; i++) {
            char cTemp = srcBuff[i];
            switch (cTemp) {
            case '&': // ת����& -->&amp;
                retBuff.append("&amp;");
                break;
            case '<': // ת����< --> &lt;
                retBuff.append("&lt;");
                break;
            case '>': // ת����> --> &gt;
                retBuff.append("&gt;");
                break;
            case '\"': // ת����" --> &quot;
                retBuff.append("&quot;");
                break;
            case '\'': // ת����' --> &apos;
                retBuff.append("&apos;");
                break;
            default:
                retBuff.append(cTemp);
            }// case
        }// end for

        return retBuff.toString();
    }

    /**
     * HTMLԪ��valueֵ���˴��������� <code> & &lt; &gt;\ </code> �������ַ���ת������
     * 
     * @sample <code>
     *    &lt;input type="text" name="Name" value="<%=CMyString.filterForHTMLValue(sContent)%>"&gt;
     * </code>
     * @param _sContent
     *            ָ�����ı�����
     * @return �������ı�����
     */
    public static String filterForHTMLValue(String _sContent) {
        if (_sContent == null)
            return "";

        // _sContent = replaceStr(_sContent,"</script>","<//script>");
        // _sContent = replaceStr(_sContent,"</SCRIPT>","<//SCRIPT>");

        char[] srcBuff = _sContent.toCharArray();
        int nLen = srcBuff.length;
        if (nLen == 0)
            return "";

        StringBuffer retBuff = new StringBuffer((int) (nLen * 1.8));

        for (int i = 0; i < nLen; i++) {
            char cTemp = srcBuff[i];
            switch (cTemp) {
            case '&': // ת����& -->&amp;why: 2002-3-19
                // caohui@0515
                // ����unicode����
                if ((i + 1) < nLen) {
                    cTemp = srcBuff[i + 1];
                    if (cTemp == '#')
                        retBuff.append("&");
                    else
                        retBuff.append("&amp;");
                } else
                    retBuff.append("&amp;");
                break;
            case '<': // ת����< --> &lt;
                retBuff.append("&lt;");
                break;
            case '>': // ת����> --> &gt;
                retBuff.append("&gt;");
                break;
            case '\"': // ת����" --> &quot;
                retBuff.append("&quot;");
                break;
            default:
                retBuff.append(cTemp);
            }// case
        }// end for

        return retBuff.toString();
    }

    /**
     * URL���˴��������� <code> # & </code> �������ַ���ת������
     * 
     * @param _sContent
     *            ָ����URL����
     * @return �������ַ���
     */
    public static String filterForUrl(String _sContent) {
        if (_sContent == null)
            return "";

        char[] srcBuff = _sContent.toCharArray();
        int nLen = srcBuff.length;
        if (nLen == 0)
            return "";

        StringBuffer retBuff = new StringBuffer((int) (nLen * 1.8));

        for (int i = 0; i < nLen; i++) {
            char cTemp = srcBuff[i];
            switch (cTemp) {
            case '%':
                retBuff.append("%25");
                break;
            case '?':
                retBuff.append("%3F");
                break;
            case '#': // ת����# --> %23
                retBuff.append("%23");
                break;
            case '&': // ת����& --> %26
                retBuff.append("%26");
                break;
            case ' ': // ת�����ո� --> %20
                retBuff.append("%20");
                break;
            default:
                retBuff.append(cTemp);
            }// case
        }// end for

        return retBuff.toString();
    }

    // why:2002-04-02 ����ת������
    /**
     * JavaScript���˴���������ָ���ı��е� <code> " \ \r \n</code> �������ַ���ת������
     * 
     * @sample <code>
     *      <br>&lt;script language="javascript"&gt;
     *      <br>     document.getElementById("id_txtName").value = "<%=CMyString.filterForJs(sValue)%>";
     *      <br>&lt;/script&gt;
     * </code>
     * @param _sContent
     *            ָ����javascript�ı�
     * @return ת���������ַ���
     */
    public static String filterForJs(String _sContent) {
        if (_sContent == null)
            return "";

        char[] srcBuff = _sContent.toCharArray();
        int nLen = srcBuff.length;
        if (nLen == 0)
            return "";

        StringBuffer retBuff = new StringBuffer((int) (nLen * 1.8));

        for (int i = 0; i < nLen; i++) {
            char cTemp = srcBuff[i];
            switch (cTemp) {
            case '"': // ת����" --> \"
                retBuff.append("\\\"");
                break;
            case '\'': // ת����' --> \'
                retBuff.append("\\\'");
                break;
            case '\\': // ת����\ --> \\
                retBuff.append("\\\\");
                break;
            case '\n':
                retBuff.append("\\n");
                break;
            case '\r':
                retBuff.append("\\r");
                break;
            case '\f':
                retBuff.append("\\f");
                break;
            case '\t':
                retBuff.append("\\t");
                break;
            case '/':
                retBuff.append("\\/");
                break;
            default:
                retBuff.append(cTemp);
            }// case
        }// end for

        return retBuff.toString();
    }

    // ==============================================================================
    // ����ת��Ϊ�ַ���

    /**
     * ��ָ������ֵת��Ϊ�ַ���
     * 
     * @see numberToStr( int _nValue, int _length, char _chrFill )
     */
    public static String numberToStr(int _nValue) {
        return numberToStr(_nValue, 0);
    }

    /**
     * ��ָ������ֵת��Ϊ�ַ���
     * 
     * @see numberToStr( int _nValue, int _length, char _chrFill )
     */
    public static String numberToStr(int _nValue, int _length) {
        return numberToStr(_nValue, _length, '0');
    }

    /**
     * ��ָ������ֵת��Ϊ�ַ���
     * 
     * @param _nValue
     *            ָ������
     * @param _length
     *            ת�����ַ������ȣ���ʵ�ʳ���С�ڸó��ȣ���ʹ��_chrFill�����; ��ѡ������ȱʡֵ0����ʾ����ʵ�ʳ��ȣ�����չ��
     * @param _chrFill
     *            ��������ʵ��λ��С��ָ������ʱ������ַ�����ѡ������ȱʡֵ'0'
     * @return ת������ַ���
     */
    public static String numberToStr(int _nValue, int _length, char _chrFill) {
        String sValue = String.valueOf(_nValue);
        return expandStr(sValue, _length, _chrFill, true);
    }

    // ���أ�ʹ��long����ֵ
    /**
     * ��ָ��������ת��Ϊ�ַ���
     * 
     * @see <code> numberToStr( long _lValue, int _length, char _chrFill ) </code>
     */
    public static String numberToStr(long _lValue) {
        return numberToStr(_lValue, 0);
    }

    /**
     * ��ָ��������ת��Ϊ�ַ���
     * 
     * @see <code> numberToStr( long _lValue, int _length, char _chrFill ) </code>
     */
    public static String numberToStr(long _lValue, int _length) {
        return numberToStr(_lValue, _length, '0');
    }

    /**
     * ��ָ��������ת��Ϊ�ַ���
     * 
     * @param _lValue
     *            ָ��������
     * @param _length
     *            ת�����ַ������ȣ���ʵ�ʳ���С�ڸó��ȣ���ʹ��_chrFill�����; ��ѡ������ȱʡֵ0����ʾ����ʵ�ʳ��ȣ�����չ��
     * @param _chrFill
     *            ��������ʵ��λ��С��ָ������ʱ������ַ�����ѡ������ȱʡֵ'0'
     * @return ת������ַ���
     */
    public static String numberToStr(long _lValue, int _length, char _chrFill) {
        String sValue = String.valueOf(_lValue);
        return expandStr(sValue, _length, _chrFill, true);
    }

    // ==============================================================================
    // �����ַ���������

    /**
     * �ַ�����ת�����ڸ������ַ��������෴��˳�����
     * 
     * @param _strSrc
     *            ָ�����ַ���
     * @return ��ת����ַ���
     */
    public static String circleStr(String _strSrc) {
        if (_strSrc == null)
            return null; // ���󱣻�

        String sResult = "";
        int nLength = _strSrc.length();
        for (int i = nLength - 1; i >= 0; i--) {
            sResult = sResult + _strSrc.charAt(i);
        }// end for
        return sResult;
    }

    /**
     * �ж�ָ�����ַ��ǲ��Ǻ��֣�Ŀǰ��ͨ���ж���ֵ�Ƿ����7FHʵ�ֵġ�
     * 
     * @param c
     *            ָ�����ַ�
     * @return �Ƿ���
     */
    public final static boolean isChineseChar(int c) {
        return c > 0x7F;
    }

    /**
     * ����ָ���ַ�����ʾ��ȣ���Ŀǰ��ʵ���У���Ϊһ��Ӣ���ַ�����ʾ�����1��һ�����ֵ���ʾ�����2��
     * 
     * @param c
     *            ָ�����ַ�
     * @return ָ���ַ�����ʾ���
     */
    public final static int getCharViewWidth(int c) {
        return isChineseChar(c) ? 2 : 1;
    }

    /**
     * ����ָ���ַ�������ʾ���
     * 
     * @param s
     *            ָ�����ַ���
     * @return ָ���ַ�������ʾ���
     */
    public final static int getStringViewWidth(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int iWidth = 0;
        int iLength = s.length();

        for (int i = 0; i < iLength; i++) {
            iWidth += getCharViewWidth(s.charAt(i));
        }

        return iWidth;
    }

    /**
     * �ַ����ضϺ�����ȡָ���ַ���ǰ��ָ�����ȵ��ַ����� ˵����Ӣ�ĺ������ַ����ȼ�1�������ַ����ȼ�2��
     * 
     * @param _string
     *            Ҫ�ضϵ��ַ�����
     * @param _maxLength
     *            �ضϳ��ȡ�
     * @return �ضϺ���ַ�������ָ������С���ַ���ʵ�ʳ��ȣ����ڷ��ص��ַ����󲹡�...��
     */
    public static String truncateStr(String _string, int _maxLength) {
        return truncateStr(_string, _maxLength, "..");
    }

    /**
     * �ַ����ضϺ�����ȡָ���ַ���ǰ��ָ�����ȵ��ַ����� ˵����Ӣ�ĺ������ַ����ȼ�1�������ַ����ȼ�2��
     * 
     * @param _string
     *            Ҫ�ضϵ��ַ�����
     * @param _maxLength
     *            �ضϳ��ȡ�
     * @param _sExt
     *            �ڽضϺ���ַ����ϵĸ��ӵ��ַ���
     * @return �ضϺ���ַ���
     */
    public static String truncateStr(String _string, int _maxLength,
            String _sExt) {
        if (_string == null) {
            return null;
        }

        if (_sExt == null) {
            _sExt = "..";
        }

        int nSrcLen = getStringViewWidth(_string);
        if (nSrcLen <= _maxLength) {
            // Դ�ַ���̫�̣�����Ҫ�ض�
            return _string;
        }

        int nExtLen = getStringViewWidth(_sExt);
        if (nExtLen >= _maxLength) {
            // Ŀ�곤��̫�̣�С���˸����ַ����ĳ��ȣ����޷��ضϡ�
            return _string;
        }

        int iLength = _string.length();
        int iRemain = _maxLength - nExtLen;
        StringBuffer sb = new StringBuffer(_maxLength + 2); // ���ӵġ�2����û������ģ�ֻ��Ϊ���ݴ�

        for (int i = 0; i < iLength; i++) {
            char aChar = _string.charAt(i);
            int iNeed = getCharViewWidth(aChar);
            if (iNeed > iRemain) {
                sb.append(_sExt);
                break;
            }
            sb.append(aChar);
            iRemain = iRemain - iNeed;
        }

        return sb.toString();
    }

    /**
     * ���˵�XML�����ܵ��ַ�
     * 
     * @param _string
     *            Դ�ַ���
     * @return
     */
    public static String filterForJDOM(String _string) {
        if (_string == null)
            return null;

        char[] srcBuff = _string.toCharArray();
        int nLen = srcBuff.length;

        StringBuffer dstBuff = new StringBuffer(nLen);

        for (int i = 0; i < nLen; i++) {
            char aChar = srcBuff[i];
            if (!isValidCharOfXML(aChar))
                continue;

            dstBuff.append(aChar); // ����Ƿ����ַ�
        }// end for
        return dstBuff.toString();

    }

    /**
     * У�鵱ǰ�ַ��Ƿ��ǺϷ���XML�ַ�
     * 
     * @param _char
     *            ��ҪУ����ַ�
     * @return
     */
    public static boolean isValidCharOfXML(char _char) {
        if (_char == 0x9 || _char == 0xA || _char == 0xD
                || (0x20 <= _char && _char <= 0xD7FF)
                || (0xE000 <= _char && _char <= 0xFFFD)
                || (0x10000 <= _char && _char <= 0x10FFFF)) {
            return true;
        }
        return false;
    }

    /**
     * �����ַ�����ռ���ֽ�����
     * <p>
     * ˵����Ӣ�ĺ������ַ����ȼ�1�������ַ����ȼ�2��
     * </p>
     * 
     * @param _string
     *            Ҫ�ضϵ��ַ�����
     * @return �ضϺ���ַ�������ָ������С���ַ���ʵ�ʳ��ȣ����ڷ��ص��ַ����󲹡�...��
     */
    public static int getBytesLength(String _string) {
        if (_string == null)
            return 0;

        char[] srcBuff = _string.toCharArray();

        int nGet = 0; // �Ѿ�ȡ�õ��ַ������ȣ����ȣ�Ӣ���ַ���1�������ַ���2��
        for (int i = 0; i < srcBuff.length; i++) {
            char aChar = srcBuff[i];
            nGet += (aChar <= 0x7f ? 1 : 2); // �����ȣ�Ӣ���ַ���1�������ַ���2��
        }// end for
        return nGet;
    }

    // �����ӿڣ���ȡ�涨���ȵ��ַ���
    // ����˵����2002-04-20 by yql����
    // ����һ���ַ�����������Ӣ�Ļ������ģ�������Ӣ�Ļ�ϵģ�ֻȡǰ���n��Ӣ����ĸռλ�Ŀ�ȡ�
    // ����ַ�������ĳ���С����Ҫ��ȡ�ĳ��ȣ���ֱ��ȡ���ַ���������
    // �����һ����Ϊ���ģ�����ǰ���Ѿ�ȡ�� n-1 λʱ���Ͳ���ȡ����֣������λ�ò�"..."��
    /**
     * �ַ����ضϺ�����ȡָ���ַ���ǰ��ָ�����ȵ��ַ�����
     * <p>
     * ˵����Ӣ�ĺ������ַ����ȼ�1�������ַ����ȼ�2��
     * </p>
     * 
     * @param _string
     *            Ҫ�ضϵ��ַ�����
     * @param _length
     *            �ضϳ��ȡ�
     * @return �ضϺ���ַ�������ָ������С���ַ���ʵ�ʳ��ȣ����ڷ��ص��ַ����󲹡�...��
     * @deprecated �Ѿ��ɺ���truncateStr���
     */
    public static String cutStr(String _string, int _length) {
        return truncateStr(_string, _length);

        /*
         * int nTmp = 0; int nLen = 0; int nMaxLen = 0; int nTotalLen = 0;
         * 
         * //�ȼ����ַ����ĳ��� for( int j=0;j <_string.length();j++ ) { if(
         * _string.charAt(j)>=0&&_string.charAt(j) <=128 ) nTotalLen += 1; else
         * nTotalLen += 2; }
         * 
         * if( nTotalLen <=_length ) { //�ַ�������ĳ���С����Ҫ��ȡ�ĳ��ȣ�ֱ��ȡ���ַ��� return
         * _string; }
         * 
         * else { //��������ж� for( int i=0;i <_length;i++ ) { if(
         * _string.charAt(i)>255 ) nTmp += 2; //�����ַ����ȼ�2 else nLen += 1;
         * //Ӣ���ַ����ȼ�1
         * 
         * nMaxLen += 1; //����
         * 
         * if( nTmp+nLen==_length ) { return ( _string.substring(0,nMaxLen)+".." ); }
         * if( nTmp+nLen>_length ) { return (
         * _string.substring(0,nMaxLen-1)+".." ); } } //end for } //end else
         * 
         * return _string; //
         */
    }

    public static String[] split(String _str, String _sDelim) {
        // String[] str
        if (_str == null || _sDelim == null) {
            return new String[0];
        }

        java.util.StringTokenizer stTemp = new java.util.StringTokenizer(_str,
                _sDelim);
        int nSize = stTemp.countTokens();
        if (nSize == 0) {
            return new String[0];
        }

        String[] str = new String[nSize];
        int i = 0;
        while (stTemp.hasMoreElements()) {
            str[i] = stTemp.nextToken().trim();
            i++;
        }// endwhile
        return str;
    }

    /**
     * ��ȡ����ָ���ķָ�����ȡ�����ַ�����
     * 
     * @param _str
     *            ָ�����ַ���
     * @param _sDelim
     *            ָ���ķָ���
     * @return �ָ����ַ�������int��
     */
    public static int countTokens(String _str, String _sDelim) {
        java.util.StringTokenizer stTemp = new java.util.StringTokenizer(_str,
                _sDelim);
        return stTemp.countTokens();
    }

    /**
     * @param _str
     *            if <code>null</code> or empty string return an array with
     *            zero length.
     * @param _sDelim
     *            if <code>null</code> or empty string then this will set to
     *            <code>,</code>
     * @return
     */
    public static int[] splitToInt(String _str, String _sDelim) {
        // wenyh@2006-3-15 16:28:35 add comment:����ǿմ�,���س���Ϊ0������
        if (isEmpty(_str)) {
            return new int[0];
        }

        // to avoid null pointer exception throw
        if (isEmpty(_sDelim)) {
            _sDelim = ",";
        }

        java.util.StringTokenizer stTemp = new java.util.StringTokenizer(_str,
                _sDelim);
        int[] arInt = new int[stTemp.countTokens()];
        int nIndex = 0;
        String sValue;
        while (stTemp.hasMoreElements()) {
            sValue = (String) stTemp.nextElement();
            arInt[nIndex] = Integer.parseInt(sValue.trim());
            nIndex++;
        }
        return arInt;
    }

    /**
     * ����XML����ʱ <BR>
     * �����CDATAǶ�����滻
     * 
     * @param _str
     * @return
     */
    public static final String encodeForCDATA(String _str) {
        if (_str == null || _str.length() < 1) {
            return _str;
        }

        return replaceStr(_str, CDATA_END, CDATA_END_REPLACER);
    }

    /**
     * ����XML���� <BR>
     * ����о���@see #encodeForCDATA(String)�滻��CDATAǶ����ԭ
     * 
     * @param _str
     * @return
     */
    public static final String decodeForCDATA(String _str) {
        if (_str == null || _str.length() < 1) {
            return _str;
        }

        return replaceStr(_str, CDATA_END_REPLACER, CDATA_END);
    }

    private static final String CDATA_END = "]]>";

    private static final String CDATA_END_REPLACER = "(TRSWCM_CDATA_END_HOLDER_TRSWCM)";

    // wenyh@2005-5-20 16:17:13 add comment:����ж��ַ������Ƿ��������ַ��ķ���

    /**
     * �ж��ַ������Ƿ���������ַ� <BR>
     * �������,�򷵻� <code>true<code>
     * 
     * @param _str ָ�����ַ���
     * @return
     */
    public static final boolean isContainChineseChar(String _str) {
        if (_str == null) {
            return false;
        }

        return (_str.getBytes().length != _str.length());
    }

    // ge add by gfc @2005-8-23 15:44:00
    /**
     * ��һ�����鰴�ո��������ӷ���������
     * 
     * @param _arColl
     *            ���в���������
     * @param _sSeparator
     *            ���ӷ�
     * @return ���Ӻ���ַ���
     */
    public static String join(ArrayList _arColl, String _sSeparator) {
        // check parameters
        if (_arColl == null)
            return null;

        // invoke reload-method and return
        return join(_arColl.toArray(), _sSeparator);
    }

    // ge add by gfc @2005-8-23 15:44:22
    /**
     * ��һ�����鰴�ո��������ӷ���������
     * 
     * @param _arColl
     *            ���в���������
     * @param _sSeparator
     *            ���ӷ�
     * @return ���Ӻ���ַ���
     */
    public static String join(Object[] _arColl, String _sSeparator) {
        // check parameters
        if (_arColl == null || _arColl.length == 0 || _sSeparator == null)
            return null;

        if (_arColl.length == 1)
            return _arColl[0].toString();

        // resolve the demiter into the string
        StringBuffer result = new StringBuffer(_arColl[0].toString());
        for (int i = 1; i < _arColl.length; i++) {
            result.append(_sSeparator);
            result.append(_arColl[i].toString());
        }

        // return the result
        return result.toString();
    }

    public static boolean containsCDATAStr(String _sValue) {
        if (_sValue == null)
            return false;

        return _sValue.matches("(?ism).*<!\\[CDATA\\[.*|.*\\]\\]>.*");
    }
  public static String transPrettyUrl(String _sUrl, int _nMaxLen) {
        return transPrettyUrl(_sUrl, _nMaxLen, null);
    }

    public static String transPrettyUrl(String _sUrl, int _nMaxLen,
            String _sSkimWord) {
        int nDemPos = 0;
        if (_sUrl == null || _nMaxLen <= 0 || _sUrl.length() <= _nMaxLen
                || (nDemPos = _sUrl.lastIndexOf('/')) == -1) {
            return _sUrl;
        }
        // else
        int nFirstPartDemPos = _sUrl.lastIndexOf("://") + 3;
        String sFirstPart = _sUrl.substring(0, nFirstPartDemPos);
        String sMidPart = _sUrl.substring(nFirstPartDemPos, nDemPos);
        if (sMidPart.length() < 3) {
            return _sUrl;
        }
        int nMidLen = (_nMaxLen + sMidPart.length() - _sUrl.length());
        if (nMidLen <= 3) {
            nMidLen = 3;
        }
        sMidPart = sMidPart.substring(0, nMidLen);
        sMidPart += (_sSkimWord != null ? _sSkimWord : "....");

        String sLastPart = _sUrl.substring(nDemPos);
        return sFirstPart + sMidPart + sLastPart;
    }

    /**
     * �滻���׺Ͷ�β�Ŀո�
     * 
     * @param _strValue
     * @return
     */
    public static String replaceStartEndSpaces(String _strValue) {
        Pattern pattern = Pattern.compile("(?m)^(\\s*)(.*?)(\\s*)$");
        Matcher matcher = pattern.matcher(_strValue);
        int nLineCount = 30;
        StringBuffer sbResult = new StringBuffer(nLineCount * 100
                + _strValue.length());
        while (matcher.find()) {
            // �滻���׵Ŀո�
            String sStartSpaces = matcher.group(1);
            for (int i = 0; i < sStartSpaces.length(); i++) {
                char c = sStartSpaces.charAt(i);
                if (c == ' ')
                    sbResult.append("&nbsp;");
                else{
                    sbResult.append(c);
                    // ׷��<BR>
                    if (c == '\n' || c == '\r')
                        sbResult.append("<BR>");
                }
            }

            // ׷������
            sbResult.append(matcher.group(2));

            // �滻��β�Ŀո�
            String sEndSpaces = matcher.group(3);
            char c = 0;
            for (int i = 0; i < sEndSpaces.length(); i++) {
                c = sEndSpaces.charAt(i);
                if (c == ' ')
                    sbResult.append("&nbsp;");
                else{
                    sbResult.append(c);
                    // ׷��<BR>
                    if (c == '\n' || c == '\r')
                        sbResult.append("<BR>");
                }
            }

        }

        return sbResult.toString();
    }

}