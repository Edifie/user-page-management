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
                <td>${u.name}</td>
                <td>${u.username}</td>
                <td>**********</td>
                <td>${u.email}</td>
                <td>${u.roles}</td>
            </tr>
            </tbody>

            <tbody>
            <tr>
                <form method="post" action="<%=request.getContextPath()%>/soa">
                    <td><input type="text" id="name" onchange="changeName()"></td>
                    <td><input type="text" id="username" onchange="changeUsername()"></td>
                    <td><input type="text" id="password" onchange="changePassword()"></td>
                    <td><input type="text" id="email" onchange="changeEmail()"></td>

                    <td>
                        <select ng-model="u.roles" ng-options="u for u in roles" id="roles" onchange="selectRole()"> </select>
                    </td>

                    <td>
                        <input type="submit" onclick="saveUser()">
                        <span class="glyphicon glyphicon-plus"></span>
                        </input>
                    </td>
                </form>
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


    function changeName() {
        userData.name = document.getElementById("name").value;

    }

    function changeUsername() {
        userData.username = document.getElementById("username").value;

    }

    function changePassword() {
        userData.password = document.getElementById("password").value;

    }

    function changeEmail() {
        userData.email = document.getElementById("email").value;

    }

    function selectRole() {
        const e = document.getElementById("roles");
        const value = e.value;
        console.log(value)

        const selectedRole = e.options[e.selectedIndex].text;
        console.log(selectedRole)
        userData.roles = selectedRole;

    }

    function saveUser() {
        send(
            "UserService",
            "addUser",
            userData,
            function (result) {
            },
        );
    }

    let app = angular.module("myApp", []);
    app.controller("myCtrl", function ($scope) {


        $scope.users = [];
        $scope.roles = ["admin", "PageCreator", "normal"];


        $scope.listUsers = function () {
            send(
                "UserService",
                "loadAll",
                {
                    "name": "name",
                    "username": "surname",
                    "password": "12345",
                },
                function (result) {
                    $scope.users = result;
                    $scope.$apply();
                },
            );
        };

        $scope.saveUser = function () {
            send(
                "UserService",
                "addUser",
                userData,
                function (result) {
                },
            );
        }

    });


</script>


</body>

</html>