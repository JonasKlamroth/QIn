import matplotlib.pyplot as plt
import numpy as np
import re

fig, ax = plt.subplots()

def readResults(fileName):
    res = {}
    with open(fileName) as f:
        lines = f.readlines()
        for line in lines:
            if (line.startswith("1: -qq")):
                name = re.search("1200000000 (\w*?) ", line).group(1)
                if (name not in res):
                    res[name] = [np.array([]) for i in range(4)]
            if (line.startswith("real")):
                times = re.search("(\d*\.\d*).*?(\d*\.\d*).*?(\d*\.\d*).*?(\d*\.\d*).*?(\d*\.\d*).*?(\d*\.\d*).*?", line)
                res[name][0] = np.append(res[name][0], float(times.group(1)))
                res[name][1] = np.append(res[name][1], float(times.group(4)))
                res[name][2] = np.append(res[name][2], float(times.group(6)))
                res[name][3] = np.append(res[name][3], float(times.group(3)))

    return res


def plotLineResults(mins, maxes, stds, means, label):
    ax.errorbar([2, 4, 6, 8], means, stds, fmt='ok', lw=3)
    ax.errorbar([2, 4, 6, 8], means, [means - mins, maxes - means],
                 fmt='.k', ecolor='gray', lw=1)

    is2qubitPlot = False
    if ("2" in label):
        is2qubitPlot = True
    label = label.replace("gatesTest2singleX", "X")
    label = label.replace("gatesTest3singleX", "X")
    label = label.replace("gatesTest2single", "H")
    label = label.replace("gatesTest3single", "H")
    label = label.replace("gatesTest2", "HH")
    label = label.replace("gatesTest3", "HHH")
    if is2qubitPlot:
        ax.plot([2, 4, 6, 8], means, label=label)
    else:
        ax.plot([2, 4, 6, 8], means, label=label, linestyle='dashed')


res = readResults("gatesResults.txt")
for name in res:
    times = res[name]
    plotLineResults(times[1], times[2], times[3], times[0], name)
ax.legend()
ax.set_title("Verification times in seconds for different gates")
ax.set_xlabel('#gates')
ax.set_ylabel('seconds')

plt.show()


res = np.array(list(readResults("qubitsResults.txt").values()))
fig, ax = plt.subplots()

ax.errorbar([1, 2, 3, 4, 5, 6, 7], np.reshape(res[:,0], (7, )), np.reshape(res[:,3], (7, )), fmt='ok', lw=3)
ax.errorbar(range(1, 8), np.reshape(res[:,0], (7,)), np.reshape([res[:,0] - res[:,1], res[:,2] - res[:,0]], (2, 7)),
                 fmt='.k', ecolor='gray', lw=1)
ax.plot(range(1, 8), res[:,0])
ax.set_title("Verification times in seconds for different number of qubits")
ax.set_xlabel('#qubits')
ax.set_ylabel('seconds')
plt.show()
