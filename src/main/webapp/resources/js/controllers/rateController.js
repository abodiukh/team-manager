app.controller("rateController", ['$scope', 'teamFactory', function ($scope, teamFactory) {

    $scope.rate = {};
    $scope.selectedRateItem = {};

    $scope.$on("team", function (event, data) {
        $scope.rate = data["rate"];
    });

    $scope.getTemplate = function (rateItem) {
        if (rateItem.id === $scope.selectedRateItem.id) {
            return 'editRate';
        } else {
            return 'displayRate';
        }
    };

    $scope.edit = function (rateItem) {
        $scope.selectedRateItem = angular.copy(rateItem);
    };

    $scope.save = function (idx) {
        $scope.rate.rateItems[idx] = angular.copy($scope.selectedRateItem);
        var team = $scope.$parent.team;
        team.rate = $scope.rate;
        teamFactory.saveTeam(team);
        $scope.reset();
    };

    $scope.remove = function (idx) {
        $scope.rate.rateItems.splice(idx, 1);
        var team = $scope.$parent.team;
        team.rate = $scope.rate;
        teamFactory.saveTeam(team);
        $scope.reset();
    };

    $scope.reset = function () {
        $scope.selectedRateItem = {};
    };
}]);
