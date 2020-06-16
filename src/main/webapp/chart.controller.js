advApp.controller('ChartController', function($scope, $routeParams, DeviceStates){

    if ($routeParams.chartType === 'temperature')
    {
        $scope.title = "Pregled istorije temperature";
        $scope.min = 0;
        $scope.max = 40;
    } else
    {
        $scope.title = "Pregled istorije vlažnosti vazduha";
        $scope.min = 0;
        $scope.max = 100;
    }

    $scope.init = function(){
        $scope.thchartConfig = {
            options: {
                chart: {type: 'line', zoomType: 'x'},
                legend: {enabled: false},
                tooltip: {
                    style: {padding: 10, fontWeight: 'bold'}
                }
            },
            series: [{data: $scope.values}],
            xAxis: {categories: $scope.labels},
            yAxis: {currentMin: $scope.min, currentMax: $scope.max, title: {text: null}},
            size: {height: 500},
            title: {text: $scope.title}
        };
    };

    $scope.filter = function(){

        $scope.values = [];
        $scope.labels = [];

        DeviceStates.query(
                {
                    dateFrom: $scope.dateFrom,
                    dateTo: $scope.dateTo
                },
        function(result){
            var i;
            for (i = 0; i < result.length; i++) {
                if ($routeParams.chartType === 'temperature')
                {
                    $scope.values[i] = Number(result[i].temperature);
                } else
                {
                    $scope.values[i] = Number(result[i].humidity);
                }
                $scope.labels[i] = result[i].createTime.substring(0, 19);
            }
            $scope.init();
        });
    };

});