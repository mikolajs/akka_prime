package pl.brosbit

class PrimeBank (size:Int) {
	var bank:List[Long] = List()

  def check(numb: Long): Long = {
    if (bank.isEmpty) {
      bank = numb :: bank
      return 0L
    } else if (!foundFactorial(numb)) {
      if ((bank.head * bank.head) > numb && bank.size < size) {
        bank = numb :: bank
        return 0L
      } else numb
    } else 0L

  }
	
	private def foundFactorial(numb:Long):Boolean = {
	  for(bNumb <- bank){
	    if(numb % bNumb == 0) return true
	  }
	  return false
	}
}