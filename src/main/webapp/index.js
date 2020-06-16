var advApp = angular.module('advApp', ["highcharts-ng", 'ngRoute',
    'ngResource', 'ngTable', 'ui.bootstrap', 'angularjs-datetime-picker']);
/*
 * Customer services
 */

advApp.factory('DeviceStates', function($resource){
    return $resource('rest/devicestates');
});

/*
 * Routing
 */
advApp.config(['$routeProvider',
    function($routeProvider){
        $routeProvider.
                when('/index', {
                    templateUrl: 'index.view.html',
                    controller: 'IndexController'
                }).
                when('/chart/:chartType', {
                    templateUrl: 'chart.view.html',
                    controller: 'ChartController'
                }).
                otherwise({
                    redirectTo: '/index'
                });
    }]);
