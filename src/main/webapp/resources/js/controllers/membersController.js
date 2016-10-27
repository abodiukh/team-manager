app.controller("membersController", ['$scope', 'teamFactory', function ($scope, teamFactory) {

    $scope.members = {};
    $scope.selectedMember = {};

    $scope.$on("team", function (event, data) {
        $scope.members = data["members"];
    });

    $scope.$on('member', function (event, data) {
        $scope.selectedMember = angular.copy(data);
    });

    $scope.getTemplate = function (member) {
        if (member.id === $scope.selectedMember.id) {
            return 'editMember';
        } else {
            return 'displayMember';
        }
    };

    $scope.edit = function (member) {
        $scope.selectedMember = angular.copy(member);
    };

    $scope.save = function (idx) {
        $scope.members[idx] = angular.copy($scope.selectedMember);
        var team = $scope.$parent.team;
        team.members = $scope.members;
        teamFactory.saveTeam(team);
        $scope.reset();
    };

    $scope.remove = function (idx) {
        $scope.members.splice(idx, 1);
        var team = $scope.$parent.team;
        team.members = $scope.members;
        teamFactory.saveTeam(team);
        $scope.reset();
    };

    $scope.reset = function () {
        $scope.selectedMember = {};
    };

}]);