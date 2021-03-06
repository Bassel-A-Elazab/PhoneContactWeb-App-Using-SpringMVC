
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List - Contact Application </title>
        <s:url var="url_css" value="/static/css/style.css"/>
        <link href="${url_css}" rel="stylesheet" type="text/css"/> 
        <s:url var="url_jqlib" value="/static/js/jquery-3.5.1.min.js" />
        <script src="${url_jqlib}"></script>
        <script>

            function changeStatus(uid, lstatus) {
                $.ajax({
                    url: 'change_status',
                    data: {userID: uid, loginStatus: lstatus},
                    success: function (data) {
                        alert(data);
                    }
                });
            }
        </script>
    </head>
    <s:url var="url_bg" value="/static/images/bg.jpg"/>
    <body background="${url_bg}">
        <table border="1" width="80%" align="center">
            <tr>
                <td height="80px">
                    <%-- Header --%>
                    <jsp:include page="include/header.jsp"/>
                </td>
            </tr>
            <tr>
                <td height="25px">
                    <%-- Menu --%>
                    <jsp:include page="include/menu.jsp"/>
                </td>
            </tr>
            <tr>
                <td height="350px" valign="top">
                    <%-- Page Content Area--%>
                    <c:if test="${param.act eq 'del'}">
                        <p class="success">User Deleted Successfully</p>
                    </c:if>
                    <h3>User List</h3>
                    <table border="1">
                        <tr>
                            <th>SR</th>
                            <th>USER ID</th>
                            <th>NAME</th>
                            <th>PHONE</th>
                            <th>EMAIL</th>
                            <th>ADDRESS</th>
                            <th>USERNAME</th>
                            <th>STATUS</th>
                        </tr>
                        <c:if test="${empty userList}">
                            <tr>
                                <td align="center" colspan="8" class="error">No Records Present</td>
                            </tr>
                        </c:if>
                        <c:forEach var="u" items="${userList}" varStatus="st">
                            <tr>
                                <td>${st.count}</td>
                                <td>${u.userID}</td>
                                <td>${u.name}</td>
                                <td>${u.phone}</td>
                                <td>${u.email}</td>
                                <td>${u.address}</td>
                                <td>${u.loginName}</td>
                                <td>
                                    <select id="id_${u.userID}" onchange="changeStatus(${u.userID}, $(this).val())">
                                        <option value="1">Active</option>
                                        <option value="2">Block</option>
                                    </select>   
                                    <script>
                                        $('#id_${u.userID}').val(${u.loginStatus});
                                    </script>
                                </td>
                                <s:url var="url_del" value="/admin/del_user">
                                    <s:param name="uid" value="${u.userID}"/>
                                </s:url>
                                <td><a href="${url_del}">Delete</a></td>
                            </tr>   
                        </c:forEach>
                    </table>

                </td>
            </tr>
            <tr>
                <td height="25px">
                    <%-- Footer --%>
                    <jsp:include page="include/footer.jsp"/>
                </td>
            </tr>
        </table>
    </body>
</html>