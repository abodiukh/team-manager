app.controller("teamController", ['$scope', function ($scope) {

    $scope.editMode = false;

    $scope.edit = function (teamName) {
        $scope.editMode = !$scope.editMode;
        $scope.$parent.editableTeam = teamName;
        if (!$scope.editMode) {
            $scope.$parent.models.selected = null;
        }
        $scope.$emit('teamChanged', {"editMode": $scope.editMode, "teamName": teamName});
    };

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
        $scope.$parent.editableTeam = $scope.team.name;
        $scope.$emit('teamChanged', {"editMode": $scope.editMode, "teamName": $scope.team.name});
    };

    $scope.addMember = function (team) {
        team.members.push({name: "Dev"});
        $scope.$parent.teams = $scope.$parent.models.lists.teams;
        localStorage.setItem("teams", JSON.stringify($scope.$parent.teams));
    };

    $scope.deleteMember = function (team, dev) {
        var index = team.members.indexOf(dev);
        team.members.splice(index, 1);
        $scope.$parent.teams = $scope.$parent.models.lists.teams;
        localStorage.setItem("teams", JSON.stringify($scope.$parent.teams));
    };

    $scope.editMember = function (team, dev) {
        $scope.$emit('editMember', dev);
    };
}]);