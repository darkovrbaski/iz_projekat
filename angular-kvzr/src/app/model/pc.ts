export interface IPC {
  cpuName: string;
  gpuName: string;
  ramName: string;
  caseName: string;
  storageName: string;
  motherboardName: string;
  powerSupplyName: string;
}

export const emptyPC = {
  cpuName: '',
  gpuName: '',
  ramName: '',
  caseName: '',
  storageName: '',
  motherboardName: '',
  powerSupplyName: '',
};

export interface IPCDescription {
  cpuName: string;
  cpuClock: number;
  gpuName: string;
  ramName: string;
  ramType: string;
  ramSize: number;
  storageName: string;
  storageType: string;
  storageSize: number;
}

export const emptyPCDescription = {
  cpuName: '',
  cpuClock: 0,
  gpuName: '',
  ramName: '',
  ramType: '',
  ramSize: 0,
  storageName: '',
  storageType: '',
  storageSize: 0,
};
