<%@ page import="teste.domain.UserImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

    </style>

</head>


<body>

<div id="myApp" class="container" ng-app="myApp" ng-controller="myCtrl">
    <div>
        <table style="width: 75%; margin-left: 1%;" class="clearfix table">
            <thead>
            <tr style="color: black">
                <th>Name</th>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th>Roles</th>
                <th>Add</th>
            </tr>
            </thead>

            <tbody ng-app="myApp" ng-controller="myCtrl" ng-repeat="u in users" class="clearfix">
            <tr style="color: black;">
                <td>${u.name}</td>
                <td>${u.username}</td>
                <td>**********</td>
                <td>${u.email}</td>
                <td>${u.roles}</td>
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
            success: function (result){
                console.log(JSON.stringify(result));
                callbackOk(result.data);
            },
            statusCode: {
                401: function (){
                    console.log(401);
                }
            },

            error: function (resposta){

            }
        });
    }

    let app = angular.module("myApp", []);
    app.controller("myCtrl", function ($scope){
        $scope.users = [];

        $scope.listUsers = function (){
            send(
                "user.UserService",
                "loadAll",
                {},
                function (result){
                    $scope.users = result;
                    $scope.$apply();
                },
            );
        };
        $scope.listUsers();
    });


</script>
</body>