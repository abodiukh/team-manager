app.controller("rateController", ['$scope', 'teamFactory', function ($scope, teamFactory) {

    $scope.rates = [];
    $scope.selectedRate = {};

    $scope.$on("team", function (event, data) {
        $scope.rates = data["rates"];
    });

    $scope.getTemplate = function (rate) {
        if (rate.id === $scope.selectedRate.id) {
            return 'editRate';
        } else {
            return 'displayRate';
        }
    };

    $scope.edit = function (rate) {
        $scope.selectedRate = angular.copy(rate);
    };

    $scope.addRate = function () {
        var teamId = $scope.$parent.team.id;
        var rate = {teamId: teamId, seniority: "", position: "", rate: 0};
        $scope.rates.push(rate);
        $scope.selectedRate = angular.copy(rate);
    };

    $scope.save = function (idx) {
        var teamId = $scope.$parent.team.id;
        $scope.rates[idx] = angular.copy($scope.selectedRate);
        teamFactory.saveRate($scope.selectedRate).success(function (data) {
            teamFactory.getTeam(teamId).success(function (data) {
                $scope.$parent.team = data;
            })
        });
        $scope.reset();
    };

    $scope.remove = function (rate) {
        var teamId = $scope.$parent.team.id;
        var rates = $scope.rates;
        $scope.rates.splice(rates.indexOf(rate), 1);
        if (rate.id) {
            teamFactory.deleteRate(rate.id).success(function (data) {
                teamFactory.getTeam(teamId).success(function (data) {
                    $scope.$parent.team = data;
                })
            });
        }
        $scope.reset();
    };

    $scope.reset = function (rate) {
        if ($scope.valid()) {
            $scope.selectedRate = {};
        } else {
            var rates = $scope.rates;
            rates.splice(rates.indexOf(rate), 1);
            $scope.$parent.team.rates = rates;
        }
    };

    $scope.valid = function () {
        return !(!$scope.selectedRate.seniority || !$scope.selectedRate.position || !$scope.selectedRate.rate);
    }

}]);
