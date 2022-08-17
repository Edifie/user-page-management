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
                <th>Add</th>
            </tr>
            </thead>

            <tbody ng-app="myApp" ng-controller="myCtrl" ng-repeat="u in users" class="clearfix">
            <tr>
                <td>{{u.name}}</td>
                <td>{{u.username}}</td>
                <td>**********</td>
                <td>{{u.email}}</td>
                <td>{{u.roles}}</td>
            </tr>
            </tbody>

            <tbody>
            <tr>
                <td><input type="text" ng-model="u.name"></td>
                <td><input type="text" ng-model="u.username"></td>
                <td><input type="text" ng-model="u.password"></td>
                <td><input type="text" ng-model="u.email"></td>

                <td>
                    <select ng-model="u.roles" ng-options="u for u in roles"></select>
                </td>

                <td>
                    <button ng-click="saveUser(u)" onclick="location.href = 'home.do'">
                        <span class="glyphicon glyphicon-plus"/>
                    </button>
                </td>

            </tr>
            </tbody>

        </table>
    </div>

</div>

<script>
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


        $scope.addUser = function (){
            let u = {
                '@class' : '<%=UserImpl.class.getName()%>'
            }
            $scope.users.push(u);
        }

        $scope.listUsers = function () {
            send(
                "user.UserService",
                "loadAll",
                {},
                function (result) {
                    $scope.users = result;
                    $scope.$apply();
                },
            );
        };

        $scope.saveUser = function (u) {
            send(
                "user.UserService",
                "addUser",
                u,
                function (result) {
                    angular.merge(u, result);
                    $scope.$apply();
                },
            );
        }

        $scope.listUsers();
    });
</script>

</body>

</html>