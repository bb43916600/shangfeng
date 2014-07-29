<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录后台</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/skin.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body{background-color: #1D3647;}
</style>
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="${ctx }/js/common.js" ></script>
<script type="text/javascript">
function login(){
  	if($.trim($("#userAccount").val())==""){ 
     	alert("账户不能为空!"); 
     	$("#userAccount").focus();
     	return false;
   	}
  	if($.trim($("#userPassWord").val())==""){ 
     	alert("密码不能为空!");
    	$("#userPassWord").focus();
    	return false;
   	}
   	if($.trim($("#verifyCode").val())==""){ 
     	alert("验证码不能为空!");
    	$("#verifyCode").focus();
    	return false;
   	} 
	$("#Submit").attr("disabled",true);
    var dc = Form.getData(document.getElementById("loginForm"));
	$.post("${ctx}/user/login.action",dc.toJSON(),function(response){
		if(response==7){
			$("#verifyCode").focus();
			$("#Submit").attr("disabled",false);
			alert("验证码不正确!");
		}else if(response==8){
			$("#userAccount").focus();
			$("#Submit").attr("disabled",false);
			alert("帐号或者密码有误！");
			$("#userAccount").attr("value","");
			$("#userPassWord").attr("value","");
		}else{
			window.location='${ctx }/index.action';
		}	
	},"json");
}
//绑定键盘回车键事件
document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13&&!$("#loginbtn").attr("disabled")!=false){
		login();
	}
}
function getEvent(a) {
	return window.event || a;
}
//刷新验证码
function changeImg(obj){
	obj.src="${ctx }/AuthCode.jsp?"+new Date().getTime();
}
</script>
</head>
<body>
<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="149">&nbsp;</td>
                </tr>
                <tr>
                  <td height="80" align="right" valign="top"><img src="images/logo.png" width="279" height="68"></td>
                </tr>
                <tr>
                  <td height="198" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="35%">&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>1- 简介简介简介简介简介...</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>2- 简介简介简介简介简介...</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>3- 简介简介简介简介简介...</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td width="30%" height="40"><img src="images/icon-demo.gif" width="16" height="16"><a href="#" target="_blank" class="left_txt3"> 使用说明</a> </td>
                      <td width="35%"><img src="images/icon-login-seaver.gif" width="16" height="16"><a href="#" class="left_txt3"> 在线客服</a></td>
                    </tr>
                  </table></td>
                </tr>
              </table></td>
            </tr>
            
        </table></td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom"><table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="96%" height="38"><span class="login_txt_bt">登陆信息网后台管理</span></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  <tr>
                    <td height="164" colspan="2" align="middle">
                    <form id="loginForm" method="post">
                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                          <tr>
                            <td width="13%" height="38" class="top_hui_text" align="right"><span class="login_txt">管理员：&nbsp;&nbsp; </span></td>
                            <td height="38" colspan="2" class="top_hui_text" align="left"><input type="text" class="inputClass" size="20" name="user.userAccount" value="bb2004" id="userAccount"/>                            </td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" class="top_hui_text" align="right"><span class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
                            <td height="35" colspan="2" class="top_hui_text" align="left"><input type="password" class="inputClass" size="20" name="user.userPassWord" value="123456" id="userPassWord"/>
                              <img src="images/luck.gif" width="19" height="18"> </td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" align="right"><span class="login_txt">验证码： &nbsp;&nbsp;</span></td>
                            <td height="35" colspan="2" class="top_hui_text" align="left">
                            	<input type="text" class="inputClass" maxLength="4" size="10" id="verifyCode" name="verifyCode" />
								<img src="${ctx }/AuthCode.jsp" alt="Haz clic en refrescar" style="vertical-align:middle;" onclick="changeImg(this);"/>
                            </td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" >&nbsp;</td>
                            <td width="20%" height="35" ><input name="Submit" type="button" class="buttonClass" id="Submit" value="登 陆" style="margin:0;" onclick="login();"> </td>
                            <td width="65%" class="top_hui_text" align="left"><input name="cs" type="reset" class="buttonClass" id="cs" value="取 消" style="margin:0;"></td>
                          </tr>
                        </table>
                        <br>
                    </form>
                    </td>
                  </tr>
                  <tr>
                    <td width="433" height="164" align="right" valign="bottom"><img src="images/login-wel.gif" width="242" height="138"></td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
          </table>
          </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
        <td align="center"><span class="login-buttom-txt">Copyright &copy; 2012-2013 www.xxx.cn</span></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
