app.controller("propertiesController", ['$scope', 'teamFactory', function ($scope, teamFactory) {

    $scope.seniorities = ["Senior", "Middle", "Junior"];
    $scope.languages = ["Java", "C#", "Php", "Python", "Javascript"];
    $scope.editMode = false;
    $scope.team = {};

    $scope.$on('parent', function (event, data) {
        $scope.team = teamFactory.getTeam(data["teamName"]);
        $scope.$broadcast('team', $scope.team);
        $scope.editMode = data["editMode"];
    });

}]);