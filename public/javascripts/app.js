'use strict';

angular.module('webClient', [
    'ui.router',
    'ngResource',
    'ngWebSocket',
    'webClient.games',
    'webClient.game_panel'
]).
config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/");
    $stateProvider
        .state('home', {
            url: "/",
            templateUrl: "assets/partials/home.html"
        });
}]).
config(["$locationProvider", function($locationProvider) {
    $locationProvider.html5Mode(true);
}]);