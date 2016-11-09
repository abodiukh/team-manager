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

    $scope.addMember = function () {
        var teamId = $scope.$parent.team.id;
        var member = {teamId: teamId, name: "Name", position: "", seniority: "", involvement: 100, vacancy: false};
        $scope.members.push(member);
        $scope.selectedMember = angular.copy(member);
    };

    $scope.save = function (idx) {
        var teamId = $scope.$parent.team.id;
        $scope.members[idx] = angular.copy($scope.selectedMember);
        $scope.$parent.team.members = $scope.members;
        teamFactory.updateMember($scope.selectedMember).success(function (data) {
            teamFactory.getTeam(teamId).success(function (data) {
                $scope.$parent.team = data;
            })
        });
        $scope.reset();
    };

    $scope.remove = function (member) {
        var teamId = $scope.$parent.team.id;
        var members = $scope.members;
        members.splice(members.indexOf(member), 1);
        $scope.$parent.team.members = members;
        if (member.id) {
            teamFactory.deleteMember(member.id).success(function (data) {
                teamFactory.getTeam(teamId).success(function (data) {
                    $scope.$parent.team = data;
                })
            });
        }
        $scope.reset();
    };

    $scope.reset = function (member) {
        if ($scope.valid()) {
            $scope.selectedMember = {};
        } else {
            var members = $scope.members;
            members.splice(members.indexOf(member), 1);
            $scope.$parent.team.members = members;
        }
    };

    $scope.valid = function () {
        return !(!$scope.selectedMember.seniority || !$scope.selectedMember.position || !$scope.selectedMember.name);
    }

}]);