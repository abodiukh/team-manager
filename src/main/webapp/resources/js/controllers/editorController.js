app.controller("editorController", ['$scope', 'teamFactory', function ($scope, teamFactory) {
    $scope.team = {};
    $scope.show = false;
    $scope.teams = [];
    $scope.editableTeam = "";

    $scope.models = {
        selected: null,
        lists: {"teams": []}
    };

    $scope.$watch('models', function (model) {
        // console.log(angular.toJson(model, true));
    }, true);

    $scope.click = function () {
        $scope.show = !$scope.show;
    };

    $scope.$on('teamChanged', function (event, data) {
        $scope.editableTeam = data["teamName"];
        $scope.$broadcast('parent', data);
    });

    $scope.$on('editMember', function (event, data) {
        $scope.$broadcast('member', data);
    });

    $scope.addTeam = function (team) {
        $scope.show = !$scope.show;
        var newTeam = new Team(team.name);
        $scope.models.lists.teams.push(newTeam);
        $scope.teams = $scope.models.lists.teams;
        localStorage.setItem("teams", JSON.stringify($scope.teams));
    };

    $scope.init = function () {
        var teams = localStorage.getItem("teams");
        if (teams == null || teams == "") {
            localStorage.setItem("teams", JSON.stringify($scope.teams));
        } else {
            $scope.teams = JSON.parse(localStorage.getItem("teams"));
            $scope.teams.forEach(function (item) {
                $scope.models.lists.teams.push(item);
            });
        }
        var rate = {
            "rateItems": [
                {"id": 0, "seniority": "Senior", "language": "Java", "amount": 3000},
                {"id": 1, "seniority": "Middle", "language": "Java", "amount": 2000},
                {"id": 2, "seniority": "Junior", "language": "Java", "amount": 1000}
            ]
        };
        var items = {};
        $scope.teams.forEach(function (team) {
            team["rate"] = angular.copy(rate);
            items[team.name] = team;
        });
        teamFactory.setData(items);
    };

    $scope.deleteTeam = function (team) {
        var index = $scope.models.lists.teams.indexOf(team);
        $scope.models.lists.teams.splice(index, 1);
        $scope.teams = $scope.models.lists.teams;
        localStorage.setItem("teams", JSON.stringify($scope.teams));
    };

    var Team = function (name) {
        this.id = teamFactory.getRandomInt(0, 100);
        this.name = name;
        this.members = [
                new Member("Senior", "Java", "Dev1"),
                new Member("Junior", "Javascript", "Dev2"),
                new Member("Middle", "Php", "Dev3"),
                new Member("Senior", "QA", "Qa1")
        ];
    };

    var Member = function (seniority, language, name) {
        this.id = teamFactory.getRandomInt(0, 100);
        this.name = name;
        this.seniority = seniority;
        this.language = language;
        this.involvement = 100;
        this.vacancy = false;
    }

}]);