app.controller("rateController",["$scope","teamFactory",function(e,t){e.rates=[],e.selectedRate={},e.$on("team",function(t,a){e.rates=a.rates}),e.getTemplate=function(t){return t.id===e.selectedRate.id?"editRate":"displayRate"},e.edit=function(t){e.selectedRate=angular.copy(t)},e.addRate=function(){var t=e.$parent.team.id,a={teamId:t,seniority:"",position:"",rate:0};e.rates.push(a),e.selectedRate=angular.copy(a)},e.save=function(a){var n=e.$parent.team.id;e.rates[a]=angular.copy(e.selectedRate),t.saveRate(e.selectedRate).success(function(a){t.getTeam(n).success(function(t){e.$parent.team=t})}),e.reset()},e.remove=function(a){var n=e.$parent.team.id,s=e.rates;e.rates.splice(s.indexOf(a),1),a.id&&t.deleteRate(a.id).success(function(a){t.getTeam(n).success(function(t){e.$parent.team=t})}),e.reset()},e.reset=function(t){if(e.valid())e.selectedRate={};else{var a=e.rates;a.splice(a.indexOf(t),1),e.$parent.team.rates=a}},e.valid=function(){return!(!e.selectedRate.seniority||!e.selectedRate.position||!e.selectedRate.rate)}}]);