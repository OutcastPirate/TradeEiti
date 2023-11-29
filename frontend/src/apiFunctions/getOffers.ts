// import { axiosBase } from "../config/axiosConfig";
import { axiosBase } from "../config/axiosConfig";
import { mockedOffers } from "../mocks/MockedOffers";
import { Offer } from "../models/Offer";

export async function getOffers() {
  return new Promise<Offer[]>((res, _) => {
    res(mockedOffers);
  });
  return axiosBase.get<Offer[]>(`/offers/pending`).then((response) => {
    return response.data;
  });
}
