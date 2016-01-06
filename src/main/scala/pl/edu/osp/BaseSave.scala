package pl.edu.osp


trait BaseSave  {

  val primeBank:PrimeBank = new PrimeBank(1000)

  def saveBank {
    println("Size of bank:" + primeBank.bank.length)
    if(primeBank.bank.size > 0) {
      val dir = new java.io.File(System.getProperty("user.dir") + "/Primes")
      if(!dir.exists) dir.mkdir
      println("create dir: " + dir.toString)
      val file = new java.io.File(dir + "/primies_%s.txt".format(primeBank.bank.head.toString))
      val output = new java.io.BufferedWriter(new java.io.FileWriter(file))
      output.write(primeBank.bank.reverse.mkString(", "))
      println("saved bank in file: " + file.toString)
      output.close()
    }
  }

}
