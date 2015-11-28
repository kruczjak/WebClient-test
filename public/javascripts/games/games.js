'use strict';

angular.module('webClient.games', [])

.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('games', {
            url: "/games",
            abstract: "true",
            template: "<div ui-view></div>"
        })
        .state("games.index", {
            url: "/?type",
            templateUrl: "assets/partials/games/index.html",
            controller: "GamesCtrl"
        })
        .state("games.new", {
            url: "/new?type",
            templateUrl: "assets/partials/games/new.html",
            controller: "GamesCtrl"
        });
}])

.controller("GamesCtrl", ['$scope', 'Games', '$stateParams', function($scope, Games, $stateParams) {
    $scope.gamesList = Games.query();
    $scope.type = $stateParams.type;
    $scope.playerList = [];

    $scope.addPlayer = function() {
      $scope.playerList.push({})
    }

}])

.factory("Games", ['$resource', function($resource) {
    return $resource('app/games', {}, {});
}]);
