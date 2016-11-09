app.factory('propertiesFactory', ['$http' , function ($http) {

    return {
        getPositions: function () {
            return $http.get('/properties/positions');
        },
        getSeniorities: function () {
            return $http.get('/properties/seniorities');
        }
    }

}]);