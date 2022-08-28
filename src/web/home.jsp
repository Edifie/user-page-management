<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
</head>
<style>

    body {
        background-color: #FAF9F6;
    }

    a {
        font-family: "Monaco", monospace;
        text-align: center;
    }

    a:link {
        color: black;
    }

    a:visited {
        color: purple;
    }

    a:hover {
        color: black;
        text-display: wavy;
    }

    a:active {
        color: black;
    }

    img {
        text-align: center;
        color: white;
        cursor: pointer;
        width: 100px;
        height: 100px;
        position: relative;
    }

    .img-user {
        display: flex;
        justify-content: space-evenly;
        padding: 15% 10% 5%;
        align-items: center;
    }

    .img-page {
        display: flex;
        justify-content: space-evenly;
        align-items: flex-end;
        padding: 0 10% 5%;
    }

</style>

<body>

<div class="img-user">


    <a href="<%=request.getContextPath()%>/addUser.do">
        <img src="./img/add-user.png">
        <p>Add User</p>
    </a>

    <a href="<%=request.getContextPath()%>/delUser.do">
        <img src="./img/remove-user.png">
        <p>Remove User</p>
    </a>

    <a href="<%=request.getContextPath()%>/listUser.do">
        <img src="./img/list-user.png">
        <p>List Users</p>
    </a>
</div>


<div class="img-page">
    <a href="<%=request.getContextPath()%>/page.do">
        <img src="./img/new-page.png">
        <p>Page</p>
    </a>

    <a href="<%=request.getContextPath()%>/delPage.do">
        <img src="./img/delete-page.png">
        <p>Remove Page</p>
    </a>

    <a href="<%=request.getContextPath()%>/listPage.do">
        <img src="./img/show-page.png">
        <p>List Pages</p>
    </a>
</div>

</body>
</html>