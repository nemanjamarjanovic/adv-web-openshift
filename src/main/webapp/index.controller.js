advApp.controller('IndexController', function($scope, $q, $interval, DeviceStates){

    var LIMIT = 6;

    $scope.valuest = [];
    $scope.valuesh = [];
    $scope.labels = [];
    $scope.lastDeviceState = {temperature: 0, humidity: 0};


    /*
     * Dovlacenje pocetnih podataka za grafikone
     */
    DeviceStates.query({limit: LIMIT},
    function(result){
        $scope.lastDeviceState = result[0];
        var i;
        for (i = 0; i < result.length; i++) {
            $scope.addValueToArrayMax10($scope.valuest, Number(result[i].temperature));
            $scope.addValueToArrayMax10($scope.valuesh, Number(result[i].humidity));
            $scope.addValueToArrayMax10($scope.labels, result[i].createTime.substring(10, 19));
        }
        $scope.initGraphs();
    });



    /*
     * Kreiranje grafikona
     */
    $scope.initGraphs = function(){

        $scope.tchartConfig = {
            options: {
                chart: {type: 'line', spacing: [10, 0, 0, 0]
                },
                legend: {enabled: false},
                tooltip: {
                    style: {padding: 10, fontWeight: 'bold'
                    }
                }
            },
            series: [{data: $scope.valuest}],
            xAxis: {categories: $scope.labels},
            yAxis: {currentMin: 0, currentMax: 40, title: {text: null}},
            size: {height: 250},
            title: {text: null}
        };
        $scope.hchartConfig = {
            options: {
                chart: {type: 'line', spacing: [10, 0, 0, 0]
                },
                legend: {enabled: false},
                tooltip: {
                    style: {padding: 10, fontWeight: 'bold'}
                }
            },
            series: [{data: $scope.valuesh}],
            xAxis: {categories: $scope.labels},
            yAxis: {currentMin: 50, currentMax: 100, title: {text: null}},
            size: {height: 250},
            title: {text: null}
        };
    };

    $scope.updateReadings = function(){

        DeviceStates.query({last: true},
        function(data){
            $scope.lastDeviceState = data[0];
            $scope.addValueToArrayMax10($scope.valuest, Number(data[0].temperature));
            $scope.addValueToArrayMax10($scope.valuesh, Number(data[0].humidity));
            $scope.addValueToArrayMax10($scope.labels, data[0].createTime.substring(10, 19));
        });

    };

    $scope.dataupdate = $interval($scope.updateReadings, 13000);

    $scope.$on('$destroy', function(){
        $interval.cancel($scope.dataupdate);
    });


    $scope.addValueToArrayMax10 = function(arrVal, val){
        if (arrVal.length > LIMIT) {
            arrVal.shift();
        }
        arrVal.push.apply(arrVal, [val]);
    };

    $scope.updateReadings();
});

