<%@ page import="teste.domain.PageImpl" %>
<%@ page import="teste.domain.Page" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!DOCTYPE html>

<head>
<style>

    body{
        align-content: center;
        background-color: #FAF9F6;
    }

    input {
        color: black;
    }

    span{
        color: black;
    }

    .glyphicon glyphicon-ok{
        color: green;
    }
</style>



</head>


<body>

<div id="myApp" class="container" ng-app="myApp" ng-controller="myCtrl">

    ${pages}
    <table class="table clearfix" style="text-align: center; border-spacing: 10px; padding-top: 20px;">
        <thead>
        <tr>
            <th>Title</th>
          <%--  <th>User ID</th>--%>
            <th>Roles</th>
        </tr>
        </thead>

        <tbody ng-app="myApp" ng-controller="myCtrl" ng-repeat="p in pages" class="clearfix">
        <tr>
            <td>${p.title}</td>
           <%-- <td>${p.userID}</td>--%>
            <td>${p.roles}</td>

            <a class="button" href="<%=request.getContextPath()%>/editPage.do?"> <%--?id=${p.id}--%>
               Pages <span style="color:seagreen" class="glyphicon-ok"></span>
            </a> </td>
        </tr>
        </tbody>

        <tbody>
        <tr>
            <form method="post" action="<%=request.getContextPath()%>/soa">
                <td><input type="text" id="title" onchange="changeTitle()"></td>
               <%-- <td><input type="text" id="userID" onchange="changeUserID()"></td>--%>

                <td>
                    <select ng-model="p.roles" ng-options="p for p in roles" id="roles" onchange="selectRole()"> </select>
                </td>

                <td>
                    <input type="submit" onclick="savePage()">
                    <span class="glyphicon glyphicon-plus"></span>
                    </input>
                </td>
            </form>
        </tr>
        </tbody>

    </table>
</div>


<script>

    const pageData = {
        title: null,
        roles: null,
        userID: null
    }


    function send(serviceName, method, data, callbackOk) {
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath()%>/soa",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({
                servico: serviceName,
                op: method,
                data: data,
            }),
            success: function (result) {
                console.log(JSON.stringify(result));
                callbackOk(result.data);
            },
            statusCode: {
                401: function () {
                    console.log(401);
                }
            },
            error: function (resposta) {

            }
        });
    }


    function changeTitle() {
        pageData.title = document.getElementById("title").value;
    }

    function changeUserID() {
        pageData.userID = document.getElementById("userID").value;
    }

    function selectRole() {
        const e = document.getElementById("roles");
        const value = e.value;
        console.log(value)

        const selectedRole = e.options[e.selectedIndex].text;
        console.log(selectedRole)
        pageData.roles = selectedRole;

    }

    function savePage(){
        send (
            "PageService",
            "addPage",
            pageData,
            function (result){
            },
        );
    }



    let app = angular.module("myApp", []);
    app.controller("myCtrl", function ($scope){

        $scope.pages = [];
        $scope.roles = ["admin", "PageCreator", "normal"];

        $scope.listPage = function () {
            send(
                "PageService",
                "loadAll",
                {
                },
                function (result) {
                    $scope.pages = result;
                    $scope.$apply();
                },
            );
        };

        $scope.savePage = function (){
            send (
                "PageService",
                "addPage",
                pageData,
                function (result){
                }
            );
        };
    });


</script>

</body>