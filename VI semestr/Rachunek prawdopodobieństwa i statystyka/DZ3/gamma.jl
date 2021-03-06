function gammaapprox(x)
	p =Float64[-1.71618513886549492533811e+0,
         2.47656508055759199108314e+1,
         -3.79804256470945635097577e+2,
         6.29331155312818442661052e+2,
         8.66966202790413211295064e+2,
         -3.14512729688483675254357e+4,
         -3.61444134186911729807069e+4,
         6.64561438202405440627855e+4]
   q =Float64[-3.08402300119738975254353e+1,
         3.15350626979604161529144e+2,
         -1.01515636749021914166146e+3,
         -3.10777167157231109440444e+3,
         2.25381184209801510330112e+4,
         4.75584627752788110767815e+3,
         -1.34659959864969306392456e+5,
         -1.15132259675553483497211e+5]
   z = x - 1.0
   a = 0
   b = 1
   for i = 1:8
		a = (a + p[i]) * z
		b = b * z + q[i]
	end
	return (a / b + 1.0);
end


function gamma(n)
	if n == round(n)
		n = convert(UInt32, n)
		return factorial(n-1)
	elseif 0 < n < 1
		return gamma(n+1.0)/n
	
   elseif n > 2
		return (n-1)*gamma(n-1)
	
	elseif n <= 0
		return pi/(sin(pi*n)*gamma(1-n))
	end
	return gammaapprox(n)
end
