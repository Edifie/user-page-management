<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="teste.domain.User" %>
<%@ page import="teste.domain.UserImpl" %>

<!DOCTYPE html>
<html>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <style>
        body {
            background-color: #FAF9F6;
            align-content: center;
        }

        table th {
            color: black;
        }

        .table {
            color: black;
            width: 75%;
            margin-left: 1%;
        }

        .table thead tr th {
            color: black;
        }

    </style>
</head>

<body>

<div id="myApp" class="container" ng-app="myApp" ng-controller="myCtrl">
    <div>


        <table style="width: 75%; margin-left: 1%;" class="clearfix table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th>Roles</th>
            </tr>
            </thead>

            <tbody ng-app="myApp" ng-controller="myCtrl" ng-repeat="u in users" class="clearfix" style="text-align: center;">
            <tr>
                <td>{{u.name}}</td>
                <td>{{u.username}}</td>
                <td>********</td>
                <td>{{u.email}}</td>
                <td>{{u.roles}}</td>
                <td>
                    <button ng-click="deleteUser(u)" onclick="window.location.reload();">
                        <span  class="glyphicon glyphicon-remove">Delete</span>
                    </button>
                </td>

                </td>
            </tr>
            </tbody>

        </table>
    </div>

</div>

<script>
    const userData = {
        name: null,
        username: null,
        password: null,
        email: null,
        roles: null
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


    let app = angular.module("myApp", []);
    app.controller("myCtrl", function ($scope) {


        $scope.users = [];
        $scope.roles = ["admin", "PageCreator", "normal"];



        $scope.listUsers = function () {
            send(
                "UserService",
                "loadAll",
                {},
                function (result) {
                    $scope.users = result;
                    $scope.$apply();
                },
            );
        };

        $scope.deleteUser = function (u) {
            send(
                "UserService",
                "deleteUser",
                u,
                function (result) {
                    angular.merge(u,result);
                    $scope.$apply();
                },
            );
        }

        $scope.listUsers();

    });

    function reflesh(){
        location.reload();
    }

</script>


</body>

</html>