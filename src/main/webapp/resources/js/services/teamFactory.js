app.factory("teamFactory", ['$http', function ($http) {

    return {
        getTeams: function () {
            return $http.get('/team/all');
        },
        getTeam: function (teamId) {
            return $http.get('/team/' + teamId);
        },
        addTeam: function (team) {
            return $http.post('/team', team);
        },
        saveTeam: function (team) {
            return $http.put('/team', team);
        },
        deleteTeam: function (teamId) {
            return $http.delete('/team/' + teamId);
        },
        addMember: function (member) {
            return $http.post('/member', member);
        },
        deleteMember: function (memberId) {
            return $http.delete('/member/' + memberId);
        },
        updateMember: function (member) {
            return $http.put('/member', member);
        },
        addRate: function (rate) {
            return $http.post('/rate', rate);
        },
        saveRate: function (rate) {
            return $http.put('/rate', rate);
        },
        deleteRate: function (rateId) {
            return $http.delete('/rate/' + rateId);
        }
    };

}]);