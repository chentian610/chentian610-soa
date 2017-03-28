/**
 * 
 */

var srtApp = angular.module('srtApp', ['angularFileUpload']);

srtApp.config([ '$locationProvider', function($locationProvider) {
	$locationProvider.html5Mode({
		enabled : true,
		requireBase : false
	});
}]);

srtApp.controller('srtController', [ '$scope', '$location', '$http', 'FileUploader',
		function($scope, $location, $http, FileUploader) {
			$scope.userId = $location.search().userId;
			$scope.ewAttrs={"ew_name":false,"ew_status":false,"ew_description":false};
			$scope.ew={};
			$scope.addModule=false;
			$scope.getUser = function() {
				$http({
					method : "POST",
					url : "http://localhost:8080/fetchModules",
					data : $scope.userId
				}).success(function(data, status) {
					$scope.wrappers=data;
				});
			};
			$scope.wrapperFocused=function(wrapper, isToShow){
				$scope.quitAdd();
				$scope.addedItems.length=0;
				for(var key in $scope.ewAttrs){
					$scope.ewAttrs[key]=false;
					$scope.restoreEW(key);
				}
				if(wrapper){
					if(isToShow==undefined){
						wrapper.showChildren=!wrapper.showChildren;
					} else {
						wrapper.showChildren=isToShow;
					}
					$scope.selectedWrapper=wrapper;
					$scope.ew['ew_name']=wrapper.name;
					$scope.ew['ew_status']=wrapper.status;
					$scope.ew['ew_description']=wrapper.description;
				}
			};
			$scope.editWrapper=function(field){
				$scope.ewAttrs[field]=!$scope.ewAttrs[field];
				if(!$scope.ewAttrs[field]){
					$scope.restoreEW(field);
				} else {
					for(var key in $scope.ewAttrs){
						if(key!=field){
							$scope.ewAttrs[key]=false;
							$scope.restoreEW(key);
						}
					}
				}
			};
			$scope.restoreEW=function(field){
				if(field=='ew_name' && $scope.ew['ew_name']){
					$scope.selectedWrapper.name=$scope.ew['ew_name'];
				} else if(field=='ew_status' && $scope.ew['ew_status']){
					$scope.selectedWrapper.status=$scope.ew['ew_status'];
				} else if(field=='ew_description' && $scope.ew['ew_description']){
					$scope.selectedWrapper.description=$scope.ew['ew_description'];
				}
			};
			$scope.saveWrapper=function(wrapper){
				$http({
					method : "POST",
					url : "http://localhost:8080/updateModules",
					data : wrapper
				}).success(function() {
					$scope.ew['ew_name']=wrapper.name;
					$scope.ew['ew_status']=wrapper.status;
					$scope.ew['ew_description']=wrapper.description;
					for(var key in $scope.ewAttrs){
						$scope.ewAttrs[key]=false;
					}
				});
			};
			$scope.addRequirement=function(wrapper){
				$scope.addModule=true;
				delete $scope.addingModule;
				delete $scope.parentOfAddingModule;
				var id=wrapper?wrapper.id:undefined;
				$scope.addingModule=new Module(id,undefined,undefined,undefined);
				$scope.parentOfAddingModule=wrapper;
			}
			$scope.submitModule=function(){
				$http({
					method:"POST",
					url:"http://localhost:8080/addModule",
					data:$scope.addingModule
				}).success(function(data){
					$scope.addingModule=data;
					if($scope.parentOfAddingModule){
						if($scope.parentOfAddingModule.subModuleIdList==null){
							$scope.parentOfAddingModule.subModuleIdList=[];
						}
						if($scope.parentOfAddingModule.module.subModuleIdList==null){
							$scope.parentOfAddingModule.module.subModuleIdList=[];
						}
						if($scope.parentOfAddingModule.subModuleWrapperList==null){
							$scope.parentOfAddingModule.subModuleWrapperList=[];
						}
						$scope.parentOfAddingModule.subModuleIdList.push($scope.addingModule.id);
						$scope.parentOfAddingModule.module.subModuleIdList.push($scope.addingModule.id);
						$scope.parentOfAddingModule.subModuleWrapperList.push($scope.addingModule);
						$scope.parentOfAddingModule.showChildren=true;
					} else {
						if($scope.wrappers){
							$scope.wrappers.push($scope.addingModule);
						} else {
							$scope.wrappers=[$scope.addingModule];
						}
					}
					$scope.wrapperFocused($scope.addingModule);
					$scope.quitAdd();
				});
			}
			$scope.quitAdd=function(){
				$scope.addModule=false;
			};
			$scope.removeRequirement=function(wrapper){
				if(confirm("Are you sure to remove this requirement and all its sub-requirment?\n"+wrapper.name)){
					$http({
						method:"POST",
						url:"http://localhost:8080/removeModule",
						data: wrapper.id
					}).success(function(){
						$scope.removeWrapperRecursively(wrapper,$scope.wrappers);
					});
				}
			};
			$scope.removeWrapperRecursively=function(wrapper, wrappers){
				if(wrapper.parentModuleId==undefined || wrapper.parentModuleId==null){
					var index=$scope.wrappers.indexOf(wrapper);
					$scope.wrappers.splice(index,1);
					if($scope.selectedWrapper==wrapper){
						if($scope.wrappers.length>0){
							$scope.wrapperFocused(index==0?$scope.wrappers[0]:$scope.wrappers[index-1],true);
						} else{
							$scope.wrapperFocused();
						}
					}
				} else {
					for(var i in wrappers){
						var w=wrappers[i];
						if(w.subModuleWrapperList!=null && w.subModuleWrapperList != undefined){
							var index=w.subModuleWrapperList.indexOf(wrapper);
							if(index>-1){
								w.subModuleWrapperList.splice(index,1);
								if($scope.isSelectedWrapperToBeRemoved(wrapper)){
									$scope.wrapperFocused(w,true);
								}
							}else {
								$scope.removeWrapperRecursively(wrapper, w.subModuleWrapperList);
							}
						}
					}
				}	
			};
			$scope.isSelectedWrapperToBeRemoved=function(wrapper){
				if($scope.selectedWrapper==wrapper){
					return true;
				}
				if(wrapper.subModuleWrapperList){
					for(var w in wrapper.subModuleWrapperList){
						var result = $scope.isSelectedWrapperToBeRemoved(wrapper.subModuleWrapperList[w]);
						if(result){
							return true;
						}
					}
				}
				return false;
			}
			$scope.getNameOfPath=function(path){
				return path.slice(path.lastIndexOf("/")+1);
			};
			$scope.uploader = new FileUploader({
		        url: 'http://localhost:8080/uploadFile',
		    });
			$scope.addedItems=[];
			$scope.uploader.onAfterAddingAll=function(addedItems){
				$scope.addedItems=$scope.addedItems.concat(addedItems);
			}
			$scope.uploader.onSuccessItem=function(item, response, status, headers) {
	            console.log(item, response, status, headers);
	            if(!$scope.selectedWrapper.attachmentPathList){
	            	$scope.selectedWrapper.attachmentPathList=[];
	            }
	            $scope.selectedWrapper.attachmentPathList.push(response);
	        }
			$scope.uploadAllFiles=function(){
				for(var q in $scope.uploader.queue){
					$scope.uploader.queue[q].formData.push({id:$scope.selectedWrapper.id});
					
				}
				$scope.uploader.uploadAll();
				$scope.addedItems.length=0;
			}
			$scope.cancelAllFiles=function(){
				$scope.uploader.clearQueue();
				$scope.addedItems.length=0;
			}
			$scope.removeFile=function(path){
				$http({
					url:'http://localhost:8080/removeFile',
					method:'POST',
					params: {moduleId:$scope.selectedWrapper.id, name:path}
				}).success(function(){
					var index=$scope.selectedWrapper.attachmentPathList.indexOf(path);
					$scope.selectedWrapper.attachmentPathList.splice(index,1);
				});
			};
			
			$scope.getUser();
		} ]);

function Module(parentModuleId, name, description, status){
	this.parentModuleId=parentModuleId;
	this.name=name;
	this.description=description;
	this.status=status;
}