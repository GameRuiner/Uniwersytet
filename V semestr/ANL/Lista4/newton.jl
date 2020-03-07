
function funnew(x, func, funprim, nmax = 15, al = 0)
	n = 0;
	while n <= nmax
		xn = x - func(x)/funprim(x)
		println("n = ", n, " x = ", xn, " f(x) = ", f(xn))
		x = xn
	end
	return m;
end