

function romberga(a, b, f, n = 15)
	R = Array{Float64}(undef, Int64(n+1))
	R[1] = (b-a)/2 * (f(a) + f(b))
	for i = 2:n+1
		h = ((b-a)/2^(i-1))
		s = 0
		oR = R[i-1]/2
		for k = 1:2^(i-2)
		s+= f(a + (2*k - 1)*h)
		end
		R[i] = oR + h * s
	end
	c = n+1
	for i = 2:n+1
		for j = 1:n-i+2
			R1 = R[j]
			R2 = R[j+1]
			k = i-1
			R[j] =  (4^k*R2 - R1)/((4^k)-1)
		end
		R[c] = 0
		c-=1
	end
	return R[1]
	
end

function G(x)
	Base.MathConstants.e^(-x^2/2)
end

function normal_distribution(t)
	Gt = romberga(0, t, G)
	Ft = 1/2 + (1/sqrt(2*pi))Gt
	return round(Ft, digits = 4)
end

println(normal_distribution(2))
println(normal_distribution(1.55))
println(normal_distribution(-1.44))
println(normal_distribution(2.34))
println(normal_distribution(1.89))