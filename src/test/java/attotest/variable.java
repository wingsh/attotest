package attotest;

public class variable {
	// ** Login Case **
	// NULL UserID & Password 
	String LoginNoInputErrMsg = "Input your id or password";
	// Invaild UserID
	String LoginInvalidUserErrMsg = "invalid password or id";
	// Invaild Password
	String LoginInvalidPasswordErrMsg = "invalid password or id";
	
	// ** Service **
	String ServiceNoInputErrMsg = "Cluster를 선택하세요.";
	String ServicemaxTitleErrMsg = "사용 가능한 글자 수를 초과 하였습니다.";
	String ServiceMaxDescriptionErrMsg = "사용 가능한 글자 수를 초과 하였습니다.";

	// ** Cluster **
	String ClusterNoInputErrMsg = "This value is required.";

	String ClusterMaxNameErrMsg = "Internal server error";
	String ClusterMaxDescriptionErrMsg = "Internal server error";
	
	// ** Cnode **
	String CnodeNullNameErrMsg = "This value is required.";
	String CnodeNullipErrMsg = "This value is required.";
	String CnodeNullHostnameErrMsg = "This value is required.";
	
	// ** Subnet **
	String SubnetNullNetworkAddressErrMsg = "This value is required.";
	String SubnetNullMaskErrMsg = "This value is required.";
	String SubnetNullvLanErrMsg = "This value is required.";
	
	// Network Address
	// NULL Network Address
	String nullNetworkAddressErrMsg = "This value is required.";
	// Invaild Network Address - Character
	String charNetworkAddressErrMsg = "Please enter a valid ip address.";
	// Invaild Network Address - Special Character
	String specialNetworkAddressErrMsg = "Please enter a valid ip address.";
	// Invaild Network Address - Range (ex.256)
	String invaildRangeNetworkAddressErrMsg = "Please enter a valid ip address.";
	
	// Mask
	// NULL Mask
	String nullMaskErrMsg = "This value is required.";
	// Invaild Mask - Character
	String charMaskErrMsg = "Please enter a valid mask."; // only e
	// Invaild Mask - Range
//	String invaildRangeMaskErrMsg = "Please enter a valid mask.";


	// Gateway
	// Invaild Gateway - Character
	String charGatewayErrMsg = "Please enter a valid IP address.";
	// Invaild Gateway - Special Character
	String specialGatewayErrMsg = "Please enter a valid IP address.";
	// Invaild Gateway - Range (ex.256)
	String invaildRangeGatewayErrMsg = "Please enter a valid IP address.";

	// vLan
	// NULL vLan
	String nullvLanErrMsg = "This value is required.";
	// Invaild vLan - Character
	String charvLanErrMsg = "Not valid number."; // only e
	// Invaild vLan - Range
//	String invaildRangevLanErrMsg = "Not valid number.";
	
	// IP Pool
	// Invaild IP - Character
	String charIPErrMsg = "Please enter a valid IP address.";
	// Invaild IP - Special Character
	String specialIPErrMsg = "Please enter a valid IP address.";
	// Invaild IP - Range (ex.256)
	String invaildRangeIPErrMsg = "Please enter a valid IP address.";

	// VIP Pool
	// Invaild VIP - Character
	String charVIPErrMsg = "Please enter a valid VIP address.";
	// Invaild VIP - Special Character
	String specialVIPErrMsg = "Please enter a valid VIP address.";
	// Invaild VIP - Range (ex.256)
	String invaildRangeVIPErrMsg = "Please enter a valid VIP address.";

	
	// ** User **


}
