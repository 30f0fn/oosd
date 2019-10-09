pub struct PrimeGenerator {
    from: u64,
    to: u64,
    pub primes: Vec<u64>
}

impl PrimeGenerator {
    pub fn new(from: u64, to: u64) -> Result<PrimeGenerator, &'static str>{
        if from >= 1 && to >= from {
            let primes = Vec::new();
            Ok(PrimeGenerator{from, to, primes})
        } else {
            Err("wrong input values: from, to")
        }
    }
    
    pub fn is_prime(&self, n: u64) -> bool {
        // println!("evaluating {} for primality", n);
        if n == 1 || (n > 2 && n % 2 == 0) {
            return false;
        }
        let mut i = 2;
        while i * i < n && n % i != 0 {
            i = i + 1;
        }
        return i * i > n;
    }

    pub fn generate_primes(&mut self) {
        for n in self.from..self.to {
            if self.is_prime(n) {
                self.primes.push(n);
            }
        }
    }

    pub fn get_primes(&self) -> &Vec<u64> {
        &self.primes
    }

}



pub mod runnable_prime_generator_mutex {
    use std::sync::{Mutex, Arc};
    use std::thread;
    use std::cmp;

    pub fn execute(num_primes : u64, num_threads : u64) {

        let search_len_max = num_primes / num_threads;

        let mut threads = Vec::new();
        let mut gens = Vec::new();

        for i in 0..num_threads {
            let offset = search_len_max * i;
            let search_len = cmp::min(num_primes, offset + search_len_max);
            let gen = Arc::new(Mutex::new(
                super::PrimeGenerator::new(offset + 1, search_len)
                    .unwrap()
            ));
            gens.push(gen);

            let gen_borrow = Arc::clone(&gens[i as usize]);
            let thread = thread::spawn(move || {
                let mut gen = gen_borrow.lock().unwrap();
                gen.generate_primes();
            });

            threads.push(thread);
        }

        for (i, thr) in threads.into_iter().enumerate() {
            thr.join().expect("thread failed!");
            // (*gens[i]).lock().unwrap().get_primes().iter().for_each(
            //     |prime| {
            //         println!("{}", prime);
            //     });
        }
    }
}



pub mod runnable_prime_generator_channel {
    use std::sync::mpsc;
    use std::thread;
    use std::cmp;

    pub fn execute(num_primes : u64, num_threads : u64) {

        let search_len_max = num_primes / num_threads;

        let mut threads = Vec::new();
        let mut gens = Vec::new();
        let (tx, rx) = mpsc::channel();

        for i in 0..num_threads {
            let offset = search_len_max * i;
            let search_len = cmp::min(num_primes, offset + search_len_max);

            let thread_tx = (&tx).clone();
            let thread = thread::spawn(move || {
                let mut gen = super::PrimeGenerator
                    ::new(offset + 1, search_len).unwrap();
                gen.generate_primes();
                thread_tx.send(gen).unwrap();
            });
            threads.push(thread);
        }

        for thr in threads.into_iter() {
            thr.join().expect("thread failed!");
            // num gens = num thrs, but ith gen need not be from the ith thread :)
            let gen = rx.recv().unwrap();
            gens.push(gen);
        }
         
        gens.sort_by_key(|g|
                         (*g).get_primes()[0]);

        // for gen in gens {
        //     gen.get_primes().iter().for_each(
        //         |prime| {
        //             println!("{}", prime);
        //         });
        // }
    }
}


fn main() {
    println!("\tRunning prime generator in Rust...");

    use std::time::{Instant};

    let thread_nums = [1, 2, 4, 8, 16];
    let search_max = 2000000;

    for num_threads in thread_nums.iter() {
        let start_time = Instant::now();
        runnable_prime_generator_channel::execute(search_max, *num_threads);
        let duration = start_time.elapsed().as_millis();
        println!("\t\tRust implementation with channels using {} threads took {} ms",
                 num_threads, duration)
    }

    for num_threads in thread_nums.iter() {
        let start_time = Instant::now();
        runnable_prime_generator_mutex::execute(search_max, *num_threads);
        let duration = start_time.elapsed().as_millis();
        println!("\t\tRust implementation with mutexes using {} threads took {} ms",
                 num_threads, duration)
    }
    
}
