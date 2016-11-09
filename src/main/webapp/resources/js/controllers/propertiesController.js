app.controller("propertiesController", ['$scope', 'teamFactory', 'propertiesFactory', function ($scope, teamFactory, propertiesFactory) {

    $scope.seniorities = [];
    $scope.positions = [];
    $scope.editMode = false;
    $scope.team = {};

    $scope.init = function () {
        propertiesFactory.getPositions().then(function (response) {
            $scope.positions = response.data;
        });
        propertiesFactory.getSeniorities().then(function (response) {
            $scope.seniorities = response.data;
        })
    };

    $scope.$on('currentTeam', function (event, data) {
        $scope.team = data["team"];
        $scope.$broadcast('team', $scope.team);
        $scope.editMode = data["editMode"];
    });

    $scope.$watch('team', function (newValue, oldValue) {
        $scope.$emit('teamChanged', {"editMode": $scope.editMode, "team": newValue});
    }, true);

}]);