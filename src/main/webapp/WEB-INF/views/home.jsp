<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="UnionApp">

  <head>
    <!--<base href="/"> -->
    <script src="resources/js/lodash.js"></script>
    <link href='https://fonts.googleapis.com/css?family=Metrophobic' rel='stylesheet' type='text/css'>
    <!-- <link rel="stylesheet" href="resources/css/angular-material.min.css" />-->
    <link rel="stylesheet" href="https://cdn.gitcdn.xyz/cdn/angular/bower-material/v1.0.0-rc7/angular-material.css">
    <link rel="stylesheet" href="resources/css/font-awesome.min.css" />
    <link rel="stylesheet" href="resources/css/abn_tree.css" />
 	<link rel="stylesheet" href="resources/css/md-data-table.min.css" />
 	<link rel="stylesheet" href="resources/css/style.css" />
 	
  </head>
  <body layout="column">
  	 <md-toolbar layout="row">
      <button ng-click="toggleSidenav('left')" hide-gt-sm class="menuBtn">
        <span class="visually-hidden">Menu</span>
      </button>
     <div layout="row" flex>
         <div flex="20" style="padding-top:10px;padding-left:10px;">
					<i class="fa fa-gg-circle" style="font-size:40px;color:white;"></i>
         </div>
    	 <span flex></span>
    	 <span flex></span>
    	 <span flex></span>
    	 <span flex></span>
    	 <span flex></span>
         <div flex style="padding-top:15px;">
         <div layout="column"><label><a class="fa fa-user" style="font-size:24px;color:white;"></a> user</label></div>
         </div>
     </div>
    </md-toolbar>
     <div layout="row" flex>
        <md-sidenav layout="column" class="md-sidenav-left md-whiteframe-z2" md-component-id="left" md-is-locked-open="$mdMedia('gt-sm')" >
          <div id="menu_container" ng-cloack ng-controller='MenuController' ng-init="retrieveAvailableMenu()">
                   <abn-tree 
                       tree-data         = "my_data"
                       tree-control      = "my_tree"
                       icon-leaf         = "fa fa-file-o"
                       icon-expand       = "fa fa-folder-o"
                       icon-collapse     = "fa fa-folder-open-o"
                       on-select         = "choosePage(branch)"
                       expand-level      = "2"
                       initial-selection = "Vegetable">      
                   </abn-tree>
               </div>
        </md-sidenav>
        <div layout="column" flex id="content">
            <md-content layout="column" flex class="md-padding" >
			<div class="view_content" ng-view>
            </div>
            </md-content>
        </div>
    </div>
  </body>
  
  <!-- Angular Material Dependencies -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
    <script src="https://cdn.gitcdn.xyz/cdn/angular/bower-material/v1.0.0-rc7/angular-material.js"></script>
    <script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/t-114/assets-cache.js"></script> 
    <script src="//cdn.jsdelivr.net/angular-material-icons/0.4.0/angular-material-icons.min.js"></script> 
    <script  src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.min.js"></script>
    <script  src="resources/js/abn_tree_directive.js"></script>
    <script  src="resources/js/md-data-table.min.js"></script>
    <!-- <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.min.js"></script> -->
  
     
   <!-- <script  src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular.js"></script>
   <script  src="resources/js/angular.min.js"></script>
   <script  src="resources/js/angular-route.min.js"></script>
   <script  src="resources/js/angular-animate.js"></script>
   <script  src="resources/js/angular-aria.min.js"></script>
   <script  src="resources/js/angular-material.min.js"></script>
   <script  src="resources/js/angular-ui-router.js"></script>
   <script  src="resources/js/abn_tree_directive.js"></script>
   <script  src="resources/js/md-data-table.min.js"></script>-->
   <!-- Main App -->
   <script  src="resources/apps/App.js"></script>
   
  <!-- Menu Module -->
  <script  src="resources/apps/modules/menu/MenuModule.js"></script>
  <script  src="resources/apps/modules/menu/controller/MenuController.js"></script>
  <script  src="resources/apps/modules/menu/controller/MasterMenuController.js"></script>
  <script  src="resources/apps/modules/menu/service/MenuService.js"></script>
  
  <!-- Navigation Module -->
  <script  src="resources/apps/modules/navigation/NavigationModule.js"></script>
  
  <!-- Stock Module -->
  <script  src="resources/apps/modules/stock/StockModule.js"></script>
  <script  src="resources/apps/modules/stock/controller/StockController.js"></script>
  <script  src="resources/apps/modules/stock/service/StockService.js"></script>
  
  <!-- Goods Module -->
  <script  src="resources/apps/modules/goods/GoodsModule.js"></script>
  <script  src="resources/apps/modules/goods/controller/CategoryController.js"></script>
   <script  src="resources/apps/modules/goods/controller/GoodsController.js"></script>
  <script  src="resources/apps/modules/goods/service/CategoryService.js"></script>
   <script  src="resources/apps/modules/goods/service/GoodsService.js"></script>
  
  <!-- Supplier Module -->
  <script  src="resources/apps/modules/supplier/SupplierModule.js"></script>
  <script  src="resources/apps/modules/supplier/controller/SupplierController.js"></script>
  <script  src="resources/apps/modules/supplier/service/SupplierService.js"></script>
  
  <!-- Price Module -->
  <script  src="resources/apps/modules/price/PriceModule.js"></script>
  <script  src="resources/apps/modules/price/controller/PriceController.js"></script>
  <script  src="resources/apps/modules/price/service/PriceService.js"></script>
  
  <!-- Discount Module -->
  <script  src="resources/apps/modules/discount/DiscountModule.js"></script>
  <script  src="resources/apps/modules/discount/controller/DiscountController.js"></script>
  <script  src="resources/apps/modules/discount/service/DiscountService.js"></script>
  
  <!-- Home Module -->
  <script  src="resources/apps/modules/order/OrderModule.js"></script>
  <script  src="resources/apps/modules/order/controller/OrderController.js"></script>
  <script  src="resources/apps/modules/order/service/OrderService.js"></script>
</body>

</html>