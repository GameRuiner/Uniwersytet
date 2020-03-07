

function romberga(a, b, f, n = 15)
	#R = Array{Float64}(undef, Int64((n+1)*(2+n)/2))
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
	#display(R)
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
		#display(R)
	end
	println(R[1])

	
end

function f2(x)
	2019*(x^5)-2018*(x^4)+2017*(x^3)
end

function f3(x)
	1/(1+25*(x^2))
end

function f4(x)
	sin(9*x+1)/x
end

romberga(-1, 2, f2)
romberga(-1, 1, f3)
romberga(1, 2*pi, f4)
