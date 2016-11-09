app.controller("teamController", ['$scope', 'teamFactory', function ($scope, teamFactory) {

    $scope.editMode = false;
    $scope.cost = 0;

    $scope.edit = function (team) {
        $scope.editMode = !$scope.editMode;
        if (!$scope.editMode) {
            $scope.$parent.models.selected = null;
            teamFactory.saveTeam($scope.team);
        }
        $scope.$emit('teamChanged', {"editMode": $scope.editMode, "team": $scope.team});
    };

    $scope.$on('currentTeam', function (event, data) {
        if (data["team"].id === $scope.team.id) {
            $scope.team = data["team"];
            $scope.editMode = data["editMode"];
        }
    });

    $scope.show = function () {
        return $scope.$parent.models.selected === $scope.team;
    };

    $scope.unselect = function () {
        if (!$scope.editMode && $scope.$parent.models.selected === $scope.team) {
            $scope.$parent.models.selected = null
        }
    };

    $scope.select = function () {
        $scope.$parent.models.selected = $scope.team;
        $scope.$parent.editableTeam = $scope.team.id;
        $scope.$emit('teamChanged', {"editMode": $scope.editMode, "team": $scope.team});
    };

    $scope.addMember = function (team) {
        var member = {teamId: team.id, name: "Name", position: "", seniority: "", involvement: 100, vacancy: false};
        $scope.team.members.push(member);
        $scope.$emit('teamChanged', {"editMode": $scope.editMode, "team": $scope.team});
        $scope.$emit('editMember', member);
    };

    $scope.deleteMember = function (team, member) {
        $scope.team.members.splice(member, 1);
        if (member.id) {
            teamFactory.deleteMember(member.id).success(function (data) {
                teamFactory.getTeam(team.id).success(function (data) {
                    $scope.team = data;
                    $scope.$emit('teamChanged', {"editMode": $scope.editMode, "team": $scope.team})
                })
            });
        }
    };

    $scope.editMember = function (team, member) {
        $scope.$emit('editMember', member);
    };

}]);