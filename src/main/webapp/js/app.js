/**
 * 
 */

var app = angular.module('Kortagleypir', []);

app.controller('mainController', ['$scope', '$http','$log', function($scope,$http,$log){
	$scope.page = 'views/users.html';
	
	$scope.gotoCards = function(){
		$scope.page = 'views/cards.html';
		
	};
	$scope.gotoUsers = function(){
		$scope.page = 'views/users.html';
		
	};
	$scope.gotoTokens = function(){
		$scope.page = 'views/tokens.html';
	};
	
}]);

app.controller('userController', ['$scope', '$http','$log', function($scope,$http,$log) {
	$scope.users = [];
	
	
	 $scope.loadData = function(){
		$http.get("user/all").success(
				function (userData) {
					$scope.users = userData;
				});
	};
	
	$scope.loadData();
}]);