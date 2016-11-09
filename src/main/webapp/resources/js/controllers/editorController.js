app.controller("editorController", ['$scope', 'teamFactory', function ($scope, teamFactory) {
    $scope.team = {};
    $scope.show = false;

    $scope.models = {
        selected: null,
        lists: {"teams": []}
    };

    $scope.init = function () {
        teamFactory.getTeams().success(function (data) {
            $scope.models.lists.teams = data;
        });
    };

    $scope.$watch('models', function (model) {
        // console.log(angular.toJson(model, true));
    }, true);

    $scope.click = function () {
        $scope.show = !$scope.show;
    };

    $scope.$on('teamChanged', function (event, data) {
        $scope.$broadcast('currentTeam', data);
    });

    $scope.$on('editMember', function (event, data) {
        $scope.$broadcast('member', data);
    });

    $scope.addTeam = function (team) {
        $scope.show = !$scope.show;
        teamFactory.addTeam({name: team.name}).success(function () {
            teamFactory.getTeams().success(function (data) {
                $scope.models.lists.teams = data;
            })
        });
    };

    $scope.deleteTeam = function (team) {
        teamFactory.deleteTeam(team.id).success(function () {
            teamFactory.getTeams().success(function (data) {
                $scope.models.lists.teams = data;
            })
        });
    };

}]);