use std;
use std::thread;

pub struct PrimeGenerator {
    from: i64,
    to: i64,
    pub primes: Vec<i64>
}

impl PrimeGenerator {
    pub fn new(from: i64, to: i64) -> Result<PrimeGenerator, &'static str>{
        if from >= 1 && to >= from {
            let primes = Vec::new();
            Ok(PrimeGenerator{from, to, primes})
        } else {
            Err("wrong input values: from, to")
            // Err(format!("wrong input values: from={}, to={}",
                        // from, to).as_str())
        }
    }
    
    fn is_even(n: i64) -> bool {
        n % 2 == 0
    }

    pub fn is_prime(&self, n: i64) -> bool {
        if n == 1 || (n > 2 && PrimeGenerator::is_even(n)) {
            return false;
        }
        let mut i = 2;
        while i * i < n && n % i != 0 {
            i = i - 1;
        }
        return i * i >= n;
    }

    pub fn generate_primes(&mut self) {
        for n in self.from..self.to {
            if self.is_prime(n) {
                self.primes.push(n);
            }
        }
    }

}


fn join_failed(thread_num: u32) {
    println!("thread {} was interrupted!", thread_num);
}


pub mod RunnablePrimeGenerator {
    pub fn main() {
        let gen1 = PrimeGenerator{from: 1, to: 100};
        let gen2 = PrimeGenerator{from: 101, to: 200};

        let thread1 = thread::spawn(|| {
            gen1.generate_primes();
        });
        let thread2 = thread::spawn(|| {
            gen2.generate_primes();
        });

        thread1.join().unwrap_or_else(||{join_failed(1)});
        thread2.join().unwrap_or_else(||{join_failed(2)});

        gen1.get_primes().for_each(
            |prime| {
                println!("{}", prime);
            });
        gen2.get_primes().for_each(
            |prime| {
                println!("{}", prime);
            });
        
        let num_primes = gen1.get_primes().size() + gen2.get_primes().size();
        println!("found a total of {} primes", num_primes);
    }
}
