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

app.controller('cardController', ['$scope', '$http','$log', function($scope,$http,$log) {
	$scope.cards = [];
	
	
	 $scope.loadData = function(){
		$http.get("card/all").success(
				function (cardData) {
					$scope.cards = cardData;
				});
	};
	
	$scope.loadData();
}]);

app.controller('tokenController', ['$scope', '$http','$log', function($scope,$http,$log) {
	$scope.tokens = [];
	
	
	 $scope.loadData = function(){
		$http.get("token/all").success(
				function (tokenData) {
					$scope.tokens = tokenData;
				});
	};
	
	$scope.loadData();
}]);
app.controller('mainCardController', ['$scope', '$http','$log', function($scope,$http,$log) {
	$scope.transactions = [];
	
	
	 $scope.loadData = function(){
		$http.get("transaction/allmain").success(
				function (transactionMainData) {
					$scope.transactions = transactionMainData;
				});
	};
	
	$scope.loadData();
}]);
app.controller('extraCardController', ['$scope', '$http','$log', function($scope,$http,$log) {
	$scope.transactions = [];
	
	
	 $scope.loadData = function(){
		$http.get("transaction/allextra").success(
				function (transactionExtraData) {
					$scope.transactions = transactionExtraData;
				});
	};
	
	$scope.loadData();
}]);
app.controller('spareCardController', ['$scope', '$http','$log', function($scope,$http,$log) {
	$scope.transactions = [];
	
	
	 $scope.loadData = function(){
		$http.get("transaction/allspare").success(
				function (transactionMainData) {
					$scope.transactions = transactionSpareData;
				});
	};
	
	$scope.loadData();
}]);