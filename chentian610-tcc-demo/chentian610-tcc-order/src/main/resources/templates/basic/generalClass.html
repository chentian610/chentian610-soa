<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//设置缓存为空   	
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires",   0);
%>

<!DOCTYPE HTML>
<html>
  <head>
  <base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>Lezhi</title>
	<link rel="stylesheet" type="text/css" href="hplus/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="hplus/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="lezhi/css/style.css">
	<link href="hplus/css/plugins/toastr/toastr.min.css" rel="stylesheet" type="text/css" />
	<link href="hplus/css/plugins/sweetalert/sweetalert.css"
		rel="stylesheet">
	<style type="text/css" media="print">
		.invite-c{width: 100%;}
		.sel,.btns{display: none}
	</style>
</head>
<body>
<div class="mainwrap">
	<div class="ftab">
		<div class="ftab-top">
			<label>已创建班级</label>
		</div>

		<div class="ftab-cont">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th>是/否已毕业</th>
						<th>入学年份</th>
						<th colspan="6">班级</th>
					</tr>
				</thead>

				<tbody id="classmenu">

					<tr class="tr_no" id="addClass">
						<td>&nbsp;</td>
						<td colspan="7">
							<a id="cBtn" href="javascript:;" style="float: left;" class="btn btn-req btn-sm" onclick="_createNew(this)">+新建</a>

							<div id="nData" style="display: none;">
								<div class="col-sm-3">
									<select name="" id="enrollment_year" class="form-control">
										<option >选择入学年份</option>
									</select>
								</div>

								<div class="col-sm-3">
									<select name="" id="classnum" class="form-control">
										<option>选择班级数量</option>
										<option>1</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
										<option>6</option>
										<option>7</option>
										<option>8</option>
										<option>9</option>
										<option>10</option>
									</select>
								</div>

								<div class="col-sm-4" style="text-align: left;">
									<a class="btn btn-req btn-sm" id="save">保存</a>
									<a href="javascript:;" class="btn btn-req btn-sm" onclick="_cancelCreate(this)">取消</a>
								</div>
							</div>
						</td>
					</tr>

				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript" src="hplus/js/jquery.min.js"></script>
<script type="text/javascript" src="js/myajax.js"></script>
<script src="hplus/js/plugins/toastr/toastr.min.js"></script>
<script src="hplus/js/plugins/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript" src="js/functionUtil.js"></script>
<script type="text/javascript" src="lezhi/js/generalClass.js?d=${time}"></script>
<script type="text/javascript">
function _createNew(obj){
	$("#cBtn").hide();
	$("#nData").show();
}

function _cancelCreate(obj){
	$("#cBtn").show();
	$("#nData").hide();
}

function _editItem(obj){
	eyear=$(obj).parent().parent().attr('code');
	var $item = $(obj).parents("tr");
	var $year = $(">td:eq(2)",$item),$classs = $(">td:eq(3)",$item);
	var year = parseInt($year.text()),classs = $("td",$classs).size();

	$(">td:eq(2),>td:eq(3)",$item).hide();

	var $_editForm = $('<td colspan="2"></td>');
	$_editForm.append('<div class="col-sm-3"><select name="" id="" class="form-control"><option value="0">选择入学年份</option></select></div><div class="col-sm-3"><select name="" id="" class="form-control"><option value="0">选择班级数量</option></select></div>');
	$_editForm.append('<div class="col-sm-4" style="text-align: left;"><a href="javascript:;" class="btn btn-req btn-sm" onclick="_submitEdit(this)">保存</a> <a href="javascript:;" class="btn btn-req btn-sm" onclick="_cancelEdit(this)">取消</a></div>');
	var date = new Date(),_thisYear = date.getFullYear(),_thisMonth=date.getMonth()+1;
	if(_thisMonth>7){
	for(var n = _thisYear-10;n<_thisYear+1;n++){
		$("select:eq(0)",$_editForm).append("<option value='"+n+"'>"+n+"</option>");
	}
	}else{
	for(var n = _thisYear-10;n<_thisYear;n++){
		$("select:eq(0)",$_editForm).append("<option value='"+n+"'>"+n+"</option>");
	}
	}
	for(var n = 1;n<=10;n++){
		$("select:eq(1)",$_editForm).append("<option value='"+n+"'>"+n+"</option>");
	}
	$item.append($_editForm);
	$("select:eq(0)",$_editForm).val(year);
	$("select:eq(1)",$_editForm).val(classs);

	$(">td:first",$item).children('a').hide();
	$(">td:eq(1)",$item).children('input').hide();

}

	function _cancelEdit(obj){
		var $item = $(obj).parents("tr");
		$(">td:eq(2),>td:eq(3)",$item).show();
		$(">td:first",$item).children('a').show();
		$(">td:eq(1)",$item).children('input').show();
		$(">td:eq(4)",$item).remove();
	}
	//session失效，页面跳转
	pageJump();
	showClass();
	showEnrollment_year();
	save();
</script>
</body>
</html>
