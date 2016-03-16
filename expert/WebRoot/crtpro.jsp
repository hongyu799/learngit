<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html
	xmlns:xdUser="http://schemas.microsoft.com/office/infopath/2006/xslt/User"
	xmlns:xdEnvironment="http://schemas.microsoft.com/office/infopath/2006/xslt/environment"
	xmlns:ipApp="http://schemas.microsoft.com/office/infopath/2006/XPathExtension/ipApp"
	xmlns:xdSignatureProperties="http://schemas.microsoft.com/office/infopath/2003/SignatureProperties"
	xmlns:sig="http://www.w3.org/2000/09/xmldsig#"
	xmlns:xdDate="http://schemas.microsoft.com/office/infopath/2003/xslt/Date"
	xmlns:xdMath="http://schemas.microsoft.com/office/infopath/2003/xslt/Math"
	xmlns:xdUtil="http://schemas.microsoft.com/office/infopath/2003/xslt/Util"
	xmlns:xdImage="http://schemas.microsoft.com/office/infopath/2003/xslt/xImage"
	xmlns:xdFormatting="http://schemas.microsoft.com/office/infopath/2003/xslt/formatting"
	xmlns:xdSolution="http://schemas.microsoft.com/office/infopath/2003/xslt/solution"
	xmlns:xdXDocument="http://schemas.microsoft.com/office/infopath/2003/xslt/xDocument"
	xmlns:xdExtension="http://schemas.microsoft.com/office/infopath/2003/xslt/extension"
	xmlns:x="urn:schemas-microsoft-com:office:excel"
	xmlns:msxsl="urn:schemas-microsoft-com:xslt"
	xmlns:xd="http://schemas.microsoft.com/office/infopath/2003"
	xmlns:my="http://schemas.microsoft.com/office/infopath/2003/myXSD/2015-09-24T01:27:09"
	xmlns:xdServerInfo="http://schemas.microsoft.com/office/infopath/2009/xslt/ServerInfo"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:xsf3="http://schemas.microsoft.com/office/infopath/2009/solutionDefinition/extensions">
	<head>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Type" content="text/html">
		<style controlStyle="controlStyle">
@media screen {
	BODY {
		margin-left: 21px;
		background-position: 21px 0px;
	}
}

BODY {
	color: windowtext;
	background-color: window;
	layout-grid: none;
}

.xdListItem {
	display: inline-block;
	width: 100%;
	vertical-align: text-top;
}

.xdListBox,.xdComboBox {
	margin: 1px;
}

.xdInlinePicture {
	margin: 1px;
	BEHAVIOR: url(#default#urn::xdPicture)
}

.xdLinkedPicture {
	margin: 1px;
	BEHAVIOR: url(#default#urn::xdPicture)
		url(#default#urn::controls/Binder)
}

.xdSection {
	border: 1pt solid #FFFFFF;
	margin: 6px 0px 6px 0px;
	padding: 1px 1px 1px 5px;
}

.xdRepeatingSection {
	border: 1pt solid #FFFFFF;
	margin: 6px 0px 6px 0px;
	padding: 1px 1px 1px 5px;
}

.xdMultiSelectList {
	margin: 1px;
	display: inline-block;
	border: 1pt solid #dcdcdc;
	padding: 1px 1px 1px 5px;
	text-indent: 0;
	color: windowtext;
	background-color: window;
	overflow: auto;
	behavior: url(#default#DataBindingUI) url(#default#urn::controls/Binder)
		url(#default#MultiSelectHelper) url(#default#ScrollableRegion);
}

.xdMultiSelectListItem {
	display: block;
	white-space: nowrap
}

.xdMultiSelectFillIn {
	display: inline-block;
	white-space: nowrap;
	text-overflow: ellipsis;;
	padding: 1px;
	margin: 1px;
	border: 1pt solid #dcdcdc;
	overflow: hidden;
	text-align: left;
}

.xdBehavior_Formatting {
	BEHAVIOR: url(#default#urn::controls/Binder) url(#default#Formatting);
}

.xdBehavior_FormattingNoBUI {
	BEHAVIOR: url(#default#CalPopup) url(#default#urn::controls/Binder)
		url(#default#Formatting);
}

.xdExpressionBox {
	margin: 1px;
	padding: 1px;
	word-wrap: break-word;
	text-overflow: ellipsis;
	overflow-x: hidden;
}

.xdBehavior_GhostedText,.xdBehavior_GhostedTextNoBUI {
	BEHAVIOR: url(#default#urn::controls/Binder) url(#default#TextField)
		url(#default#GhostedText);
}

.xdBehavior_GTFormatting {
	BEHAVIOR: url(#default#urn::controls/Binder) url(#default#Formatting)
		url(#default#GhostedText);
}

.xdBehavior_GTFormattingNoBUI {
	BEHAVIOR: url(#default#CalPopup) url(#default#urn::controls/Binder)
		url(#default#Formatting) url(#default#GhostedText);
}

.xdBehavior_Boolean {
	BEHAVIOR: url(#default#urn::controls/Binder) url(#default#BooleanHelper)
		;
}

.xdBehavior_Select {
	BEHAVIOR: url(#default#urn::controls/Binder) url(#default#SelectHelper);
}

.xdBehavior_ComboBox {
	BEHAVIOR: url(#default#ComboBox)
}

.xdBehavior_ComboBoxTextField {
	BEHAVIOR: url(#default#ComboBoxTextField);
}

.xdRepeatingTable {
	BORDER-TOP-STYLE: none;
	BORDER-RIGHT-STYLE: none;
	BORDER-LEFT-STYLE: none;
	BORDER-BOTTOM-STYLE: none;
	BORDER-COLLAPSE: collapse;
	WORD-WRAP: break-word;
}

.xdScrollableRegion {
	BEHAVIOR: url(#default#ScrollableRegion);
}

.xdLayoutRegion {
	display: inline-block;
}

.xdMaster {
	BEHAVIOR: url(#default#MasterHelper);
}

.xdActiveX {
	margin: 1px;
	BEHAVIOR: url(#default#ActiveX);
}

.xdFileAttachment {
	display: inline-block;
	margin: 1px;
	BEHAVIOR: url(#default#urn::xdFileAttachment);
}

.xdPageBreak {
	display: none;
}

BODY {
	margin-right: 21px;
}

.xdTextBoxRTL {
	display: inline-block;
	white-space: nowrap;
	text-overflow: ellipsis;;
	padding: 1px;
	margin: 1px;
	border: 1pt solid #dcdcdc;
	color: windowtext;
	background-color: window;
	overflow: hidden;
	text-align: right;
	word-wrap: normal;
}

.xdRichTextBoxRTL {
	display: inline-block;;
	padding: 1px;
	margin: 1px;
	border: 1pt solid #dcdcdc;
	color: windowtext;
	background-color: window;
	overflow-x: hidden;
	word-wrap: break-word;
	text-overflow: ellipsis;
	text-align: right;
	font-weight: normal;
	font-style: normal;
	text-decoration: none;
	vertical-align: baseline;
}

.xdDTTextRTL {
	height: 100%;
	width: 100%;
	margin-left: 22px;
	overflow: hidden;
	padding: 0px;
	white-space: nowrap;
}

.xdDTButtonRTL {
	margin-right: -21px;
	height: 18px;
	width: 20px;
	behavior: url(#default#DTPicker);
}

.xdMultiSelectFillinRTL {
	display: inline-block;
	white-space: nowrap;
	text-overflow: ellipsis;;
	padding: 1px;
	margin: 1px;
	border: 1pt solid #dcdcdc;
	overflow: hidden;
	text-align: right;
}

.xdTextBox {
	display: inline-block;
	white-space: nowrap;
	text-overflow: ellipsis;;
	padding: 1px;
	margin: 1px;
	border: 1pt solid #dcdcdc;
	color: windowtext;
	background-color: window;
	overflow: hidden;
	text-align: left;
	word-wrap: normal;
}

.xdRichTextBox {
	display: inline-block;;
	padding: 1px;
	margin: 1px;
	border: 1pt solid #dcdcdc;
	color: windowtext;
	background-color: window;
	overflow-x: hidden;
	word-wrap: break-word;
	text-overflow: ellipsis;
	text-align: left;
	font-weight: normal;
	font-style: normal;
	text-decoration: none;
	vertical-align: baseline;
}

.xdDTPicker {;
	display: inline;
	margin: 1px;
	margin-bottom: 2px;
	border: 1pt solid #dcdcdc;
	color: windowtext;
	background-color: window;
	overflow: hidden;
	text-indent: 0
}

.xdDTText {
	height: 100%;
	width: 100%;
	margin-right: 22px;
	overflow: hidden;
	padding: 0px;
	white-space: nowrap;
}

.xdDTButton {
	margin-left: -21px;
	height: 18px;
	width: 20px;
	behavior: url(#default#DTPicker);
}

.xdRepeatingTable TD {
	VERTICAL-ALIGN: top;
}
</style>
		<style tableEditor="TableStyleRulesID">
TABLE.xdLayout TD {
	BORDER-BOTTOM: medium none;
	BORDER-LEFT: medium none;
	BORDER-TOP: medium none;
	BORDER-RIGHT: medium none
}

TABLE.msoUcTable TD {
	BORDER-BOTTOM: 1pt solid;
	BORDER-LEFT: 1pt solid;
	BORDER-TOP: 1pt solid;
	BORDER-RIGHT: 1pt solid
}

TABLE {
	BEHAVIOR: url ( #default #urn :: tables/ NDTable )
}
</style>
		<style languageStyle="languageStyle">
BODY {
	FONT-SIZE: 10pt;
	FONT-FAMILY: Microsoft YaHei
}

SELECT {
	FONT-SIZE: 10pt;
	FONT-FAMILY: Microsoft YaHei
}

TABLE {
	FONT-SIZE: 10pt;
	FONT-FAMILY: Microsoft YaHei;
	TEXT-TRANSFORM: none;
	FONT-WEIGHT: normal;
	COLOR: black;
	FONT-STYLE: normal
}

.optionalPlaceholder {
	FONT-SIZE: 9pt;
	TEXT-DECORATION: none;
	FONT-FAMILY: Microsoft YaHei;
	FONT-WEIGHT: normal;
	COLOR: #333333;
	FONT-STYLE: normal;
	PADDING-LEFT: 20px;
	BEHAVIOR: url(#default#xOptional)
}

.langFont {
	FONT-SIZE: 10pt;
	FONT-FAMILY: Microsoft YaHei;
	WIDTH: 150px
}

.defaultInDocUI {
	FONT-SIZE: 9pt;
	FONT-FAMILY: Microsoft YaHei
}

.optionalPlaceholder {
	PADDING-RIGHT: 20px
}
</style>
		<style themeStyle="urn:office.microsoft.com:themeSummer">
TABLE {
	BORDER-TOP: medium none;
	BORDER-RIGHT: medium none;
	BORDER-COLLAPSE: collapse;
	BORDER-BOTTOM: medium none;
	BORDER-LEFT: medium none
}

TD {
	BORDER-TOP-COLOR: #d8d8d8;
	BORDER-LEFT-COLOR: #d8d8d8;
	BORDER-BOTTOM-COLOR: #d8d8d8;
	BORDER-RIGHT-COLOR: #d8d8d8
}

TH {
	BORDER-TOP-COLOR: #000000;
	BORDER-LEFT-COLOR: #000000;
	COLOR: black;
	BORDER-BOTTOM-COLOR: #000000;
	BORDER-RIGHT-COLOR: #000000;
	BACKGROUND-COLOR: #f2f2f2
}

.xdTableHeader {
	COLOR: black;
	BACKGROUND-COLOR: #f2f2f2
}

.light1 {
	BACKGROUND-COLOR: #ffffff
}

.dark1 {
	BACKGROUND-COLOR: #000000
}

.light2 {
	BACKGROUND-COLOR: #f7f8f4
}

.dark2 {
	BACKGROUND-COLOR: #2b4b4d
}

.accent1 {
	BACKGROUND-COLOR: #6c9a7f
}

.accent2 {
	BACKGROUND-COLOR: #bb523d
}

.accent3 {
	BACKGROUND-COLOR: #c89d11
}

.accent4 {
	BACKGROUND-COLOR: #fccf10
}

.accent5 {
	BACKGROUND-COLOR: #568ea1
}

.accent6 {
	BACKGROUND-COLOR: #decf28
}
</style>
		<style tableStyle="Professional">
TR.xdTitleRow {
	MIN-HEIGHT: 83px
}

TD.xdTitleCell {
	BORDER-TOP: #bfbfbf 1pt solid;
	BORDER-RIGHT: #bfbfbf 1pt solid;
	PADDING-BOTTOM: 14px;
	TEXT-ALIGN: center;
	PADDING-TOP: 32px;
	PADDING-LEFT: 22px;
	BORDER-LEFT: #bfbfbf 1pt solid;
	PADDING-RIGHT: 22px;
	BACKGROUND-COLOR: #ffffff;
	valign: bottom
}

TR.xdTitleRowWithHeading {
	MIN-HEIGHT: 69px
}

TD.xdTitleCellWithHeading {
	BORDER-TOP: #bfbfbf 1pt solid;
	BORDER-RIGHT: #bfbfbf 1pt solid;
	PADDING-BOTTOM: 0px;
	TEXT-ALIGN: center;
	PADDING-TOP: 32px;
	PADDING-LEFT: 22px;
	BORDER-LEFT: #bfbfbf 1pt solid;
	PADDING-RIGHT: 22px;
	BACKGROUND-COLOR: #ffffff;
	valign: bottom
}

TR.xdTitleRowWithSubHeading {
	MIN-HEIGHT: 75px
}

TD.xdTitleCellWithSubHeading {
	BORDER-TOP: #bfbfbf 1pt solid;
	BORDER-RIGHT: #bfbfbf 1pt solid;
	PADDING-BOTTOM: 6px;
	TEXT-ALIGN: center;
	PADDING-TOP: 32px;
	PADDING-LEFT: 22px;
	BORDER-LEFT: #bfbfbf 1pt solid;
	PADDING-RIGHT: 22px;
	BACKGROUND-COLOR: #ffffff;
	valign: bottom
}

TR.xdTitleRowWithOffsetBody {
	MIN-HEIGHT: 72px
}

TD.xdTitleCellWithOffsetBody {
	BORDER-TOP: #bfbfbf 1pt solid;
	BORDER-RIGHT: #bfbfbf 1pt solid;
	PADDING-BOTTOM: 2px;
	TEXT-ALIGN: left;
	PADDING-TOP: 32px;
	PADDING-LEFT: 22px;
	BORDER-LEFT: #bfbfbf 1pt solid;
	PADDING-RIGHT: 22px;
	BACKGROUND-COLOR: #ffffff;
	valign: bottom
}

TR.xdTitleHeadingRow {
	MIN-HEIGHT: 37px
}

TD.xdTitleHeadingCell {
	BORDER-RIGHT: #bfbfbf 1pt solid;
	PADDING-BOTTOM: 14px;
	TEXT-ALIGN: center;
	PADDING-TOP: 0px;
	PADDING-LEFT: 22px;
	BORDER-LEFT: #bfbfbf 1pt solid;
	PADDING-RIGHT: 22px;
	BACKGROUND-COLOR: #ffffff;
	valign: top
}

TR.xdTitleSubheadingRow {
	MIN-HEIGHT: 70px
}

TD.xdTitleSubheadingCell {
	BORDER-RIGHT: #bfbfbf 1pt solid;
	PADDING-BOTTOM: 16px;
	PADDING-TOP: 8px;
	PADDING-LEFT: 22px;
	BORDER-LEFT: #bfbfbf 1pt solid;
	PADDING-RIGHT: 22px;
	BACKGROUND-COLOR: #ffffff;
	valign: top
}

TD.xdVerticalFill {
	BORDER-TOP: #bfbfbf 1pt solid;
	BORDER-BOTTOM: #bfbfbf 1pt solid;
	BORDER-LEFT: #bfbfbf 1pt solid;
	BACKGROUND-COLOR: #354d3f
}

TD.xdTableContentCellWithVerticalOffset {
	BORDER-RIGHT: #bfbfbf 1pt solid;
	BORDER-BOTTOM: #bfbfbf 1pt solid;
	PADDING-BOTTOM: 2px;
	TEXT-ALIGN: left;
	PADDING-TOP: 32px;
	PADDING-LEFT: 95px;
	BORDER-LEFT: #bfbfbf 1pt solid;
	PADDING-RIGHT: 0px;
	BACKGROUND-COLOR: #ffffff;
	valign: bottom
}

TR.xdTableContentRow {
	MIN-HEIGHT: 140px
}

TD.xdTableContentCell {
	BORDER-RIGHT: #bfbfbf 1pt solid;
	BORDER-BOTTOM: #bfbfbf 1pt solid;
	PADDING-BOTTOM: 0px;
	PADDING-TOP: 0px;
	PADDING-LEFT: 0px;
	BORDER-LEFT: #bfbfbf 1pt solid;
	PADDING-RIGHT: 0px;
	BACKGROUND-COLOR: #ffffff;
	valign: top
}

TD.xdTableContentCellWithVerticalFill {
	BORDER-RIGHT: #bfbfbf 1pt solid;
	BORDER-BOTTOM: #bfbfbf 1pt solid;
	PADDING-BOTTOM: 0px;
	PADDING-TOP: 0px;
	PADDING-LEFT: 1px;
	BORDER-LEFT: #bfbfbf 1pt solid;
	PADDING-RIGHT: 1px;
	BACKGROUND-COLOR: #ffffff;
	valign: top
}

TD.xdTableStyleOneCol {
	PADDING-BOTTOM: 4px;
	PADDING-TOP: 4px;
	PADDING-LEFT: 22px;
	PADDING-RIGHT: 22px
}

TR.xdContentRowOneCol {
	MIN-HEIGHT: 45px;
	valign: center
}

TR.xdHeadingRow {
	MIN-HEIGHT: 27px
}

TD.xdHeadingCell {
	BORDER-TOP: #a6c2b2 1pt solid;
	BORDER-BOTTOM: #a6c2b2 1pt solid;
	PADDING-BOTTOM: 2px;
	TEXT-ALIGN: center;
	PADDING-TOP: 2px;
	PADDING-LEFT: 22px;
	PADDING-RIGHT: 22px;
	BACKGROUND-COLOR: #e1eae5;
	valign: bottom
}

TR.xdSubheadingRow {
	MIN-HEIGHT: 28px
}

TD.xdSubheadingCell {
	BORDER-BOTTOM: #a6c2b2 1pt solid;
	PADDING-BOTTOM: 4px;
	TEXT-ALIGN: center;
	PADDING-TOP: 4px;
	PADDING-LEFT: 22px;
	PADDING-RIGHT: 22px;
	valign: bottom
}

TR.xdHeadingRowEmphasis {
	MIN-HEIGHT: 27px
}

TD.xdHeadingCellEmphasis {
	BORDER-TOP: #a6c2b2 1pt solid;
	BORDER-BOTTOM: #a6c2b2 1pt solid;
	PADDING-BOTTOM: 2px;
	TEXT-ALIGN: center;
	PADDING-TOP: 2px;
	PADDING-LEFT: 22px;
	PADDING-RIGHT: 22px;
	BACKGROUND-COLOR: #e1eae5;
	valign: bottom
}

TR.xdSubheadingRowEmphasis {
	MIN-HEIGHT: 28px
}

TD.xdSubheadingCellEmphasis {
	BORDER-BOTTOM: #a6c2b2 1pt solid;
	PADDING-BOTTOM: 4px;
	TEXT-ALIGN: center;
	PADDING-TOP: 4px;
	PADDING-LEFT: 22px;
	PADDING-RIGHT: 22px;
	valign: bottom
}

TR.xdTableLabelControlStackedRow {
	MIN-HEIGHT: 45px
}

TD.xdTableLabelControlStackedCellLabel {
	PADDING-BOTTOM: 4px;
	PADDING-TOP: 4px;
	PADDING-LEFT: 22px;
	PADDING-RIGHT: 5px
}

TD.xdTableLabelControlStackedCellComponent {
	PADDING-BOTTOM: 4px;
	PADDING-TOP: 4px;
	PADDING-LEFT: 5px;
	PADDING-RIGHT: 22px
}

TR.xdTableRow {
	MIN-HEIGHT: 30px
}

TD.xdTableCellLabel {
	PADDING-BOTTOM: 4px;
	PADDING-TOP: 4px;
	PADDING-LEFT: 22px;
	PADDING-RIGHT: 5px
}

TD.xdTableCellComponent {
	PADDING-BOTTOM: 4px;
	PADDING-TOP: 4px;
	PADDING-LEFT: 5px;
	PADDING-RIGHT: 22px
}

TD.xdTableMiddleCell {
	PADDING-BOTTOM: 4px;
	PADDING-TOP: 4px;
	PADDING-LEFT: 5px;
	PADDING-RIGHT: 5px
}

TR.xdTableEmphasisRow {
	MIN-HEIGHT: 30px
}

TD.xdTableEmphasisCellLabel {
	PADDING-BOTTOM: 4px;
	PADDING-TOP: 4px;
	PADDING-LEFT: 22px;
	PADDING-RIGHT: 5px;
	BACKGROUND-COLOR: #c4d6cb
}

TD.xdTableEmphasisCellComponent {
	PADDING-BOTTOM: 4px;
	PADDING-TOP: 4px;
	PADDING-LEFT: 5px;
	PADDING-RIGHT: 22px;
	BACKGROUND-COLOR: #c4d6cb
}

TD.xdTableMiddleCellEmphasis {
	PADDING-BOTTOM: 4px;
	PADDING-TOP: 4px;
	PADDING-LEFT: 5px;
	PADDING-RIGHT: 5px;
	BACKGROUND-COLOR: #c4d6cb
}

TR.xdTableOffsetRow {
	MIN-HEIGHT: 30px
}

TD.xdTableOffsetCellLabel {
	PADDING-BOTTOM: 4px;
	PADDING-TOP: 4px;
	PADDING-LEFT: 22px;
	PADDING-RIGHT: 5px;
	BACKGROUND-COLOR: #c4d6cb
}

TD.xdTableOffsetCellComponent {
	PADDING-BOTTOM: 4px;
	PADDING-TOP: 4px;
	PADDING-LEFT: 5px;
	PADDING-RIGHT: 22px;
	BACKGROUND-COLOR: #c4d6cb
}

P {
	FONT-SIZE: 11pt;
	COLOR: #354d3f;
	MARGIN-TOP: 0px
}

H1 {
	MARGIN-BOTTOM: 0px;
	FONT-SIZE: 24pt;
	FONT-WEIGHT: normal;
	COLOR: #354d3f;
	MARGIN-TOP: 0px
}

H2 {
	MARGIN-BOTTOM: 0px;
	FONT-SIZE: 16pt;
	FONT-WEIGHT: bold;
	COLOR: #354d3f;
	MARGIN-TOP: 0px
}

H3 {
	MARGIN-BOTTOM: 0px;
	FONT-SIZE: 12pt;
	TEXT-TRANSFORM: uppercase;
	FONT-WEIGHT: normal;
	COLOR: #354d3f;
	MARGIN-TOP: 0px
}

H4 {
	MARGIN-BOTTOM: 0px;
	FONT-SIZE: 10pt;
	FONT-WEIGHT: normal;
	COLOR: #262626;
	FONT-STYLE: italic;
	MARGIN-TOP: 0px
}

H5 {
	MARGIN-BOTTOM: 0px;
	FONT-SIZE: 10pt;
	FONT-WEIGHT: bold;
	COLOR: #354d3f;
	FONT-STYLE: italic;
	MARGIN-TOP: 0px
}

H6 {
	MARGIN-BOTTOM: 0px;
	FONT-SIZE: 10pt;
	FONT-WEIGHT: normal;
	COLOR: #262626;
	MARGIN-TOP: 0px
}

BODY {
	COLOR: black
}
</style>
		<link href="http://192.168.1.254:8085/pub/zjk/images/infoview/infoview_pub.css" rel="stylesheet"
			type="text/css" />
	</head>
	<body>
		<form target="_top" action="http://localhost:8086/infogate/collect"
			name="frmAction" id="frmAction">
			<input value="5" name="SiteId" type="hidden" />
			<input value="81" name="ChannelId" type="hidden" />
			<input value="2" name="InfoViewId" type="hidden" />
			<input name="DocumentId" type="hidden" />
			<input value="false" name="NeedInit" type="hidden" />
			<input value="false" name="OnlyCached" type="hidden" />
			<textarea style="display: none" name="ObjectXML">
			<?xml version="1.0" encoding="UTF-8"?>
<?mso-infoPathSolution name="urn:schemas-microsoft-com:office:infopath:9G1e-oQi-k-X1:-myXSD-2015-09-24T01-27-09" href="manifest.xsf" solutionVersion="1.0.0.14" productVersion="12.0.0" PIVersion="1.0.0.0" ?>
<?mso-application progid="InfoPath.Document" versionProgid="InfoPath.Document.2"?><my:myFields xmlns:my="http://schemas.microsoft.com/office/infopath/2003/myXSD/2015-09-24T01:27:09" xmlns:xsf3="http://schemas.microsoft.com/office/infopath/2009/solutionDefinition/extensions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xdServerInfo="http://schemas.microsoft.com/office/infopath/2009/xslt/ServerInfo" xmlns:xd="http://schemas.microsoft.com/office/infopath/2003">
	<my:项目名称/>
	<my:项目背景/>
	<my:网络工程>false</my:网络工程>
	<my:软件开发>false</my:软件开发>
	<my:系统集成>false</my:系统集成>
	<my:信息安全>false</my:信息安全>
	<my:机房建设>false</my:机房建设>
	<my:运维管理>false</my:运维管理>
	<my:提交人/>
	<my:专家人数/>
</my:myFields></textarea>
			<input value="UTF-8" name="encoding" type="hidden" />
			<input value="http://localhost:8086/infogate/public/initpage.jsp"
				name="GateWayInit" type="hidden" />
			<input
				value="http://localhost:8086/infogate/file/file_server_upload.jsp"
				name="fileuploadurl" type="hidden" />
			<input
				value="http://localhost:8086/infogate/file/file_server_read.jsp"
				name="readfileurl" type="hidden" />
			<input
				value="http://localhost:8086/infogate/customer/demo_login.html?jerk=1"
				name="GATEWAYLOGIN" type="hidden" />
			<input value="../images/infoview/" name="resourcebase" type="hidden" />
			<input value="项目" name="InfoviewTitle" type="hidden" />
			<input value name="CachedInfoviewId" type="hidden" />
			<input value name="JustCached" type="hidden" />
		</form>
		<form name="frmData" id="frmData">
			<div align="center">
				<table border="1" class="xdFormLayout msoUcTable"
					style="BORDER-BOTTOM-STYLE: none; BORDER-LEFT-STYLE: none; WIDTH: 812px; BORDER-COLLAPSE: collapse; WORD-WRAP: break-word; BORDER-TOP-STYLE: none; TABLE-LAYOUT: fixed; BORDER-RIGHT-STYLE: none">
					<colgroup>
						<col style="WIDTH: 812px">
					</colgroup>
					<tbody>
						<tr class="xdTitleRow" style="MIN-HEIGHT: 83px">
							<td
								style="BORDER-BOTTOM-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; VERTICAL-ALIGN: middle"
								class="xdTitleCell">
								<h1 align="center">
									<span style="FONT-SIZE: 15pt"><strong>吉林省电子政务<font
											face="Microsoft YaHei">项目登记表</font>
									</strong>
									</span>
								</h1>
							</td>
						</tr>
						<tr class="xdTableContentRow" style="MIN-HEIGHT: 301px">
							<td
								style="BORDER-BOTTOM-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; VERTICAL-ALIGN: middle"
								class="xdTableContentCell">
								<div align="left">
									<table borderColor="buttontext" border="1" class="xdLayout"
										style="BORDER-BOTTOM: medium none; BORDER-LEFT: medium none; WIDTH: 807px; BORDER-COLLAPSE: collapse; WORD-WRAP: break-word; TABLE-LAYOUT: fixed; BORDER-TOP: medium none; BORDER-RIGHT: medium none">
										<colgroup>
											<col style="WIDTH: 172px">
											<col style="WIDTH: 635px">
										</colgroup>
										<tbody vAlign="top">
											<tr style="MIN-HEIGHT: 31px">
												<td
													style="BORDER-BOTTOM: #000000 1pt solid; BORDER-LEFT: #000000 1pt solid; VERTICAL-ALIGN: middle; BORDER-TOP: #000000 1pt solid; BORDER-RIGHT: #000000 1pt solid">
													<div align="center">
														<font face="微软雅黑" size="2">项目名称：</font>
													</div>
												</td>
												<td
													style="BORDER-BOTTOM: #000000 1pt solid; BORDER-LEFT: #000000 1pt solid; VERTICAL-ALIGN: middle; BORDER-TOP: #000000 1pt solid; BORDER-RIGHT: #000000 1pt solid">
													<div align="center">
														<font face="微软雅黑" size="2"><span not_null="1"
															data_type="string" data_pattern="string"
															validation="type:'string',max_len:500,required:true,required:true"
															trs_backreadonly_field="0" trs_readonly_field="0"
															gateway_binding="" default_value="" trs_obj_id="38"
															trs_field_name="项目名称" pattern="string" elname="项目名称"
															trs_obj_type="1105" trs_obj_typename="DataField"
															name="my:项目名称" id="my:项目名称" trs_temp_id="my:项目名称"
															style="WIDTH: 100%" xd:xctname="PlainText"
															xd:CtrlId="CTRL1" xd:binding="my:项目名称" tabIndex="0"
															title="项目名称" class="xdTextBox" hideFocus="1"></span>
														</font>
													</div>
												</td>
											</tr>
											<tr style="MIN-HEIGHT: 47px">
												<td
													style="BORDER-BOTTOM: #000000 1pt solid; BORDER-LEFT: #000000 1pt solid; VERTICAL-ALIGN: middle; BORDER-TOP: #000000 1pt solid; BORDER-RIGHT: #000000 1pt solid">
													<div align="center">
														<font face="微软雅黑" size="2">所属专业：</font>
													</div>
												</td>
												<td
													style="BORDER-BOTTOM: #000000 1pt solid; BORDER-LEFT: #000000 1pt solid; VERTICAL-ALIGN: middle; BORDER-TOP: #000000 1pt solid; BORDER-RIGHT: #000000 1pt solid">
													<div align="center">
														<font face="微软雅黑" size="2"><input
																data_type="string" data_pattern
																validation="type:'string',max_len:50"
																trs_backreadonly_field="0" trs_readonly_field="0"
																gateway_binding default_value trs_obj_id="39"
																trs_field_name="软件开发" pattern="string" elname="软件开发"
																trs_obj_type="1105" trs_obj_typename="DataField"
																name="my:软件开发" id="my:软件开发" trs_temp_id="my:软件开发"
																xd:boundProp="xd:value" xd:offValue="false"
																xd:onValue="true" xd:xctname="CheckBox"
																xd:CtrlId="CTRL6" xd:binding="my:软件开发" tabIndex="0"
																type="checkbox" value title="软件开发"
																class="xdBehavior_Boolean" xd:value="false" />软件开发&nbsp;&nbsp;
															&nbsp;&nbsp; </font>
														<input data_type="string" data_pattern
															validation="type:'string',max_len:50"
															trs_backreadonly_field="0" trs_readonly_field="0"
															gateway_binding default_value trs_obj_id="40"
															trs_field_name="系统集成" pattern="string" elname="系统集成"
															trs_obj_type="1105" trs_obj_typename="DataField"
															name="my:系统集成" id="my:系统集成" trs_temp_id="my:系统集成"
															xd:boundProp="xd:value" xd:offValue="false"
															xd:onValue="true" xd:xctname="CheckBox" xd:CtrlId="CTRL7"
															xd:binding="my:系统集成" tabIndex="0" type="checkbox" value
															title="系统集成" class="xdBehavior_Boolean" xd:value="false" />
														系统集成&nbsp; &nbsp; &nbsp;
														<input data_type="string" data_pattern
															validation="type:'string',max_len:50"
															trs_backreadonly_field="0" trs_readonly_field="0"
															gateway_binding default_value trs_obj_id="41"
															trs_field_name="网络工程" pattern="string" elname="网络工程"
															trs_obj_type="1105" trs_obj_typename="DataField"
															name="my:网络工程" id="my:网络工程" trs_temp_id="my:网络工程"
															xd:boundProp="xd:value" xd:offValue="false"
															xd:onValue="true" xd:xctname="CheckBox" xd:CtrlId="CTRL8"
															xd:binding="my:网络工程" tabIndex="0" type="checkbox" value
															title="网络工程" class="xdBehavior_Boolean" xd:value="false" />
														网络工程&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
														<input data_type="string" data_pattern
															validation="type:'string',max_len:50"
															trs_backreadonly_field="0" trs_readonly_field="0"
															gateway_binding default_value trs_obj_id="42"
															trs_field_name="信息安全" pattern="string" elname="信息安全"
															trs_obj_type="1105" trs_obj_typename="DataField"
															name="my:信息安全" id="my:信息安全" trs_temp_id="my:信息安全"
															xd:boundProp="xd:value" xd:offValue="false"
															xd:onValue="true" xd:xctname="CheckBox" xd:CtrlId="CTRL9"
															xd:binding="my:信息安全" tabIndex="0" type="checkbox" value
															title="信息安全" class="xdBehavior_Boolean" xd:value="false" />
														信息安全&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
														<input data_type="string" data_pattern
															validation="type:'string',max_len:50"
															trs_backreadonly_field="0" trs_readonly_field="0"
															gateway_binding default_value trs_obj_id="43"
															trs_field_name="机房建设" pattern="string" elname="机房建设"
															trs_obj_type="1105" trs_obj_typename="DataField"
															name="my:机房建设" id="my:机房建设" trs_temp_id="my:机房建设"
															xd:boundProp="xd:value" xd:offValue="false"
															xd:onValue="true" xd:xctname="CheckBox"
															xd:CtrlId="CTRL10" xd:binding="my:机房建设" tabIndex="0"
															type="checkbox" value title="机房建设"
															class="xdBehavior_Boolean" xd:value="false" />
														机房建设&nbsp;&nbsp; &nbsp; &nbsp;
														<input data_type="string" data_pattern
															validation="type:'string',max_len:50"
															trs_backreadonly_field="0" trs_readonly_field="0"
															gateway_binding default_value trs_obj_id="44"
															trs_field_name="运维管理" pattern="string" elname="运维管理"
															trs_obj_type="1105" trs_obj_typename="DataField"
															name="my:运维管理" id="my:运维管理" trs_temp_id="my:运维管理"
															xd:boundProp="xd:value" xd:offValue="false"
															xd:onValue="true" xd:xctname="CheckBox"
															xd:CtrlId="CTRL11" xd:binding="my:运维管理" tabIndex="0"
															type="checkbox" value title="运维管理"
															class="xdBehavior_Boolean" xd:value="false" />
														运维管理
													</div>
												</td>
											</tr>
											<tr style="MIN-HEIGHT: 216px">
												<td
													style="BORDER-BOTTOM: #000000 1pt solid; BORDER-LEFT: #000000 1pt solid; VERTICAL-ALIGN: middle; BORDER-TOP: #000000 1pt solid; BORDER-RIGHT: #000000 1pt solid">
													<div align="center">
														<font face="微软雅黑" size="2">项目背景：</font>
													</div>
												</td>
												<td
													style="BORDER-BOTTOM: #000000 1pt solid; BORDER-LEFT: #000000 1pt solid; VERTICAL-ALIGN: middle; BORDER-TOP: #000000 1pt solid; BORDER-RIGHT: #000000 1pt solid">
													<div align="center">
														<font face="微软雅黑" size="2"><span not_null="1"
															data_type="string" data_pattern="string"
															validation="type:'string',max_len:2000,required:true,required:true"
															trs_backreadonly_field="0" trs_readonly_field="0"
															gateway_binding="" default_value="" trs_obj_id="45"
															trs_field_name="项目背景" pattern="string" elname="项目背景"
															trs_obj_type="1105" trs_obj_typename="DataField"
															name="my:项目背景" id="my:项目背景" trs_temp_id="my:项目背景"
															style="OVERFLOW-X: auto; OVERFLOW-Y: auto; WIDTH: 629px; WORD-WRAP: break-word; WHITE-SPACE: normal; HEIGHT: 189px"
															xd:datafmt="&quot;string&quot;,&quot;plainMultiline&quot;"
															xd:xctname="PlainText" xd:CtrlId="CTRL3"
															xd:binding="my:项目背景" tabIndex="0" title="项目背景"
															class="xdTextBox" hideFocus="1"></span>
														</font>
													</div>
												</td>
											</tr>
											<tr style="MIN-HEIGHT: 38px">
												<td
													style="BORDER-BOTTOM: #000000 1pt solid; BORDER-LEFT: #000000 1pt solid; VERTICAL-ALIGN: middle; BORDER-TOP: #000000 1pt solid; BORDER-RIGHT: #000000 1pt solid">
													<div align="center">
														<font face="微软雅黑" size="2">专家人数：</font>
													</div>
												</td>
												<td
													style="BORDER-BOTTOM: #000000 1pt solid; BORDER-LEFT: #000000 1pt solid; VERTICAL-ALIGN: middle; BORDER-TOP: #000000 1pt solid; BORDER-RIGHT: #000000 1pt solid">
													<font face="微软雅黑" size="2">
														<div align="left">
															<span not_null="1" data_type="string"
																data_pattern="string"
																validation="type:'string',max_len:500,required:true,required:true"
																trs_backreadonly_field="0" trs_readonly_field="0"
																gateway_binding="" default_value="" trs_obj_id="94"
																trs_field_name="专家人数" pattern="string" elname="专家人数"
																trs_obj_type="1105" trs_obj_typename="DataField"
																name="my:专家人数" id="my:专家人数" trs_temp_id="my:专家人数"
																style="BACKGROUND-COLOR: #ffffff; WIDTH: 255px; COLOR: #000000"
																xd:xctname="PlainText" xd:CtrlId="CTRL13"
																xd:binding="my:专家人数" tabIndex="0" title="专家人数"
																class="xdTextBox" hideFocus="1"></span>
														</div> </font>
												</td>
											</tr>
											<tr style="MIN-HEIGHT: 39px">
												<td
													style="BORDER-BOTTOM: #000000 1pt solid; BORDER-LEFT: #000000 1pt solid; VERTICAL-ALIGN: middle; BORDER-TOP: #000000 1pt solid; BORDER-RIGHT: #000000 1pt solid">
													<div align="center">
														<font face="微软雅黑" size="2">提交人：</font>
													</div>
												</td>
												<td
													style="BORDER-BOTTOM: #000000 1pt solid; BORDER-LEFT: #000000 1pt solid; VERTICAL-ALIGN: middle; BORDER-TOP: #000000 1pt solid; BORDER-RIGHT: #000000 1pt solid">
													<font face="微软雅黑" size="2">
														<div align="left">
															<span data_type="string" data_pattern="string"
																validation="type:'string',max_len:500"
																trs_backreadonly_field="0" trs_readonly_field="1"
																gateway_binding="" default_value="" trs_obj_id="92"
																trs_field_name="提交人" pattern="string" elname="提交人"
																trs_obj_type="1105" trs_obj_typename="DataField"
																name="my:提交人" id="my:提交人" trs_temp_id="my:提交人"
																style="WIDTH: 256px" xd:xctname="PlainText"
																xd:CtrlId="CTRL12" xd:binding="my:提交人" tabIndex="0"
																title="提交人" class="xdTextBox" hideFocus="1"></span>
														</div> </font>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div align="center">
				&nbsp;
			</div>
		</form>
		<div align="center">
			<input value="提交" type="button" name="SubmitButton" id="SubmitButton" />
			<input value="重置" type="reset" name="ResetButton" id="ResetButton" />
		</div>
		<script
			src="http://192.168.1.254:8085/pub/zjk/images/infoview/wcmlib.js"></script>
		<script
			src="http://192.168.1.254:8085/pub/zjk/images/infoview/infoview_pub.js"></script>
	</body>
</html>