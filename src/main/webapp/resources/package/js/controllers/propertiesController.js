app.controller("propertiesController",["$scope","teamFactory","propertiesFactory",function(e,t,o){e.seniorities=[],e.positions=[],e.employees=[],e.editMode=!1,e.team={},e.init=function(){o.getPositions().then(function(t){e.positions=t.data}),o.getSeniorities().then(function(t){e.seniorities=t.data}),o.getEmployees().then(function(t){e.employees=t.data})},e.$on("currentTeam",function(t,o){e.team=o.team,e.$broadcast("team",e.team),e.editMode=o.editMode}),e.$watch("team",function(t,o){e.$emit("teamChanged",{editMode:e.editMode,team:t})},!0)}]);