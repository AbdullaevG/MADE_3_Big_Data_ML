#!/usr/bin/env python
"""reducer_var.py"""

import sys


if __name__ == '__main__':
    size, mean, var = 0, 0, 0
    for row in sys.stdin:
        row = row.strip()
        chunk_size, chunk_mean, chunk_var = map(float, row.split("\t"))

        var += (chunk_size*chunk_var + size*var) / (size+chunk_size)
        var += size*chunk_size * ((chunk_mean - mean)/(size+chunk_size))**2
        mean += (chunk_size*chunk_mean + size*mean) / (size+chunk_size)

        size += chunk_size

    print(var)

