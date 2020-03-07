
function funbis(a, b, func, nmax = 15, al = 0)
	n = 0;
	m = a+b/2;
	ao = a
	bo = b
	while n <= nmax
		println("a = ", a, " b = ", b) 
		m = (a+b)/2;
		fm = func(m);
		fa = func(a)
		if (fm * fa < 0)
			b = m
		end
		if (fm * fa > 0)
			a = m
		end
		if (fm == 0)
			return m;
		end
		println("fm = ", fm, " m = ", m, " n = ", n)
		println("en ", abs(al-fm), " <= ", 2.0^(-n-1)*(bo-ao))	
		n+=1
	end
	return m;
end