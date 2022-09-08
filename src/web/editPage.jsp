<%@ page import="teste.domain.*" %>
<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>


<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


<style>

    body {
        background-color: #FAF9F6;
    }

</style>

<html>


<%
    String id = request.getParameter("id");
%>

<div id="myApp" ng-app="myApp" ng-controller="myCtrl">

    <h2 style="color:black;text-align: center; padding-bottom: 3%;"> ${page.title}</h2>

    <div class="container">


        TITULO NOVA: <input class="input" type="text" ng-model="newtitle">
        <button type="button" ng-click="saveSection(newtitle)"><span class="glyphicon glyphicon-plus"></span> </button>

        <h3 style="color:black; margin-left:auto; margin-right: auto; text-align: center;">Edit Sections</h3>

        <table class="table" style="margin-left: auto; margin-right: auto; padding-bottom: 5%;">
            <thead>
            <tr>
                <th style="color: black">Title</th>
                <th>Save Section</th>
                <th>Delete Section</th>
            </tr>
            </thead>

            <tbody>
            <tr  ng-repeat="s in page.sections">
                <td style="color:black">${s.title}</td>
                <td colspan="2"></td>
                <td>

                    <button class="button" ng-click="deleteSections(s)" >
                        Delete Section
                    </button>
                </td>
            </tr>
            </tbody>



        </table>


        <!-------------------------------------------------------------------------------------------------->

        <h3 style="color:black; text-align: center;"> Edit Components</h3>

        <table ng-app="myApp" ng-controller="myCtrl" class="table" ng-repeat="s in page.sections"
               style="margin-left: auto; margin-right: auto;">
            <thead>
            <tr>
                <th>
                    ${s.title}
                    <input type="button" value="Create Component" id="btnComp" ng-click="ShowHide(s.id)"/>
                </th>
                <th>
                    Image Path
                </th>
                <th>
                    Text
                </th>
                <th>
                    Save
                </th>
                <th>
                    Delete
                </th>
            </tr>
            </thead>

            </tbody>
            <tr ng-repeat="c in s.components">
                <td style="color:black">
                    ${c.text}
                </td>
                <td style="color:black">
                    ${c.img}
                </td>
                <td colspan="1"></td>

            </tr>
            </tbody>
            <tbody>
            <tr ng-show="isVisible">
                <form method="post" action="<%=request.getContextPath()%>/soa">
                <td>
                    <input type="text" ng-model="c.text">
                </td>
                <td ng-controller="myCtrl">
                    <input file-model="myFile" style="color:black" type="file" class="form-control" id="myFileField"/>
                </td>
                <td>
                    <input type="text" id="text" onchange="changeText()">
                </td>
                <td>
                    <button style="color:black" ng-click="saveComponent(c)" onclick="window.location.reload();">
                        Save Component
                    </button>
                </td>

                <td>
                    <button class="button" ng-click="deleteComponent(c)" onclick="window.location.reload();">
                        Delete Component
                    </button>
                </td>
                </form>
            </tr>
            </tbody>

        </table>

        <pre>
            {{pages | json}}
        </pre>


    </div>
</div>


</html>


<script>


    const sectionData = {
        title: null,
        text: null
    }

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
            dataType: 'json',
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


    function changeTitle(){
        sectionData.title = document.getElementById("title").value;
    }
    function changeText(){
        sectionData.text = document.getElementById("text").value;
    }


    let app = angular.module("myApp", []);

    app.directive('fileModel', ['$parse', function ($parse) {

        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                let model = $parse(attrs.fileModel);
                let modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);

    app.service('fileUpload', ['$http', function ($http) {

        this.uploadFileToUrl = function (file, uploadUrl) {
            let fd = new FormData();
            fd.append('file', file);
            $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
                .success(function () {

                })
                .error(function () {

                });
        }

    }]);


    app.controller('myCtrl', ['$scope', 'fileUpload', function ($scope, fileUpload) {

        $scope.myFile = [];
        $scope.id = <%=id%>;
        $scope.idSec = [];
        $scope.isVisible = false;

        $scope.page = {
            sections: [
                {components: []}
            ]
        };

        let file = $scope.myFile;
        $scope.ShowHide = function (idSec) {
            $scope.idSec = idSec;
            $scope.isVisible = $scope.isVisible ? false : true;
            $scope.apply();
        };

        $scope.loadPage = function () {
            send(
                "PageService",
                "loadPage",
                {
                    id: $scope.id,
                },
                function (result) {
                    $scope.page = result;
                    $scope.apply();
                }
            );
        };

        <%--
        $scope.savePage = function () {
            send(
                "PageService",
                "addPage",
                $scope.page,
                function (result) {
                    angular.merge($scope.page, result);
                    $scope.apply();
                }
            );
        };
        --%>

        $scope.savePage = function (){
            send (
                "PageService",
                "addPage",
                pageData,
                function (result){
                },
            );
        }


        $scope.saveSection = function (batatas) {
            send(
                "SectionService",
                "addSection",
                {
                    idPage: $scope.id,
                    title: batatas,
                },
                function (seccao) {
                   $scope.page.sections.push(seccao);
                    $scope.apply();
                },
            );
        };


        $scope.saveComponent = function (c) {
            let uploadUrl = "<%request.getContextPath();%>/img/";
            send(
                "ComponentService",
                "addComponent",
                {
                    idSection: $scope.idSec,
                    text: c.text,
                    img: file.toString(),
                },
                function (result) {
                    fileUpload.uploadFileToUrl(file, uploadUrl);
                    angular.merge(c, result);
                    $scope.apply();
                },
            );
        };

        $scope.deleteSection = function (s) {
            send(
                "SectionService",
                "deleteSection",
                {
                    s,
                    "idSection": s.id,
                },
                function (result) {
                    let i = $scope.page.sections.indexOf(s);
                    $scope.page.sections.splice(i,1);
                    $scope.apply();
                },
                function (error) {
                    alert(error);
                    $scope.apply();
                }
            );
        };

        $scope.deleteComponent = function (c) {
            send(
                "ComponentService",
                "deleteComp",
                {
                    id: c.id
                },
                function (result) {
                    angular.merge(c, result);
                    $scope.apply();
                },
            );
        };
        $scope.loadPage();


    }]);

</script>