'use strict';

angular.module('webClient.game_panel', [])

.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('game_panel', {
            url: "/game_panel",
            abstract: "true",
            template: "<div ui-view></div>"
        })
        .state("game_panel.index", {
            url: "/?mode",
            templateUrl: "assets/partials/game_panel/index.html",
            controller: "GamePanelCtrl"
        });
}])

.controller("GamePanelCtrl", ['$scope', '$websocket', function($scope, $websocket) {
    var dataStream = $websocket('ws://localhost:9000/app/socketio');
    $scope.data = [];

    dataStream.onMessage(function(message) {
        $scope.data.push(message.data);
    });
}])

.factory("GamePanel", ['$resource', function($resource) {
    return $resource('app/games', {}, {});
}]);