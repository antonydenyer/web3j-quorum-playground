pragma solidity >=0.5.0 <0.7.0;

contract VendingMachine {
    function buy(uint amount) public payable {
        require(
            amount <= msg.value / 2 ether,
            "Not enough Ether provided."
        );
    }
}