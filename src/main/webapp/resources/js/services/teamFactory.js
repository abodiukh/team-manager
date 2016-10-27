app.factory("teamFactory", function () {
    var data = {
        teams: {}
    };

    return {
        getTeam: function (name) {
            return data.teams[name];
        },
        saveTeam: function (name, team) {
            data.teams[name] = team;
        },
        setData: function (teams) {
            data.teams = teams;
        },
        getRandomInt: function (min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        }
    };

});