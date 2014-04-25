package com.nerdery.university.nucalc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nerdery.university.nucalc.service.SharedPreferenceService;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalculatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "CalculatorFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String KEY_INPUT_STRING = "keyInputString";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // UI Elements
    private EditText txtInput;
    private EditText txtOutput;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnZero;
    private Button btnPoint;
    private Button btnEquals;
    private Button btnPlus;
    private Button btnMinus;
    private Button btnMultiply;
    private Button btnDivide;

    // Variables
    private String inputString = "";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "The onActivityCreated method");
        super.onActivityCreated(savedInstanceState);

        if( getActivity() != null ) {
            SharedPreferenceService prefs = new SharedPreferenceService(getActivity());
            inputString = prefs.getString(KEY_INPUT_STRING, "");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "The onCreate() method");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "The onCreateView() method");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        // Edit Text
        txtInput = (EditText) view.findViewById(R.id.textInput);
        txtOutput = (EditText) view.findViewById(R.id.textOutput);

        // Buttons
        btnOne = (Button) view.findViewById(R.id.buttonOne);
        btnOne.setOnClickListener(this);

        btnTwo = (Button) view.findViewById(R.id.buttonTwo);
        btnTwo.setOnClickListener(this);

        btnThree = (Button) view.findViewById(R.id.buttonThree);
        btnThree.setOnClickListener(this);

        btnFour = (Button) view.findViewById(R.id.buttonFour);
        btnFour.setOnClickListener(this);

        btnFive = (Button) view.findViewById(R.id.buttonFive);
        btnFive.setOnClickListener(this);

        btnSix = (Button) view.findViewById(R.id.buttonSix);
        btnSix.setOnClickListener(this);

        btnSeven = (Button) view.findViewById(R.id.buttonSeven);
        btnSeven.setOnClickListener(this);

        btnEight = (Button) view.findViewById(R.id.buttonEight);
        btnEight.setOnClickListener(this);

        btnNine = (Button) view.findViewById(R.id.buttonNine);
        btnNine.setOnClickListener(this);

        btnZero = (Button) view.findViewById(R.id.buttonZero);
        btnZero.setOnClickListener(this);

        btnPoint = (Button) view.findViewById(R.id.buttonPoint);
        btnPoint.setOnClickListener(this);

        btnEquals = (Button) view.findViewById(R.id.buttonEquals);
        btnEquals.setOnClickListener(this);

        btnPlus = (Button) view.findViewById(R.id.buttonPlus);
        btnPlus.setOnClickListener(this);

        btnMinus = (Button) view.findViewById(R.id.buttonMinus);
        btnMinus.setOnClickListener(this);

        btnMultiply = (Button) view.findViewById(R.id.buttonMultiply);
        btnMultiply.setOnClickListener(this);

        btnDivide = (Button) view.findViewById(R.id.buttonDivide);
        btnDivide.setOnClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void logAction(String logString) {
        if (mListener != null) {
            mListener.onCalculatorFragmentInteraction(logString);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(TAG, "The onAttach() method");
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "The onDetach() method");
        super.onDetach();
        mListener = null;
    }

    /**
     * Save the fragment state
     *
     * @param outState The out state bundle
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "The onSaveInstanceState(Bundle)");
        super.onSaveInstanceState(outState);

        if( getActivity() != null ) {
            SharedPreferenceService prefs = new SharedPreferenceService(getActivity());
            prefs.saveString(KEY_INPUT_STRING, inputString);
        }
    }

    /**
     * Handle the fragment button clicks
     *
     * @param view The view that was clicked
     */
    @Override
    public void onClick(final View view) {
        Log.d(TAG, "The onClick() method called with view id [" + view.getId() + "]");
        switch(view.getId()) {
            case R.id.buttonOne:
                inputString += "1";
                break;
            case R.id.buttonTwo:
                inputString += "2";
                break;
            case R.id.buttonThree:
                inputString += "3";
                break;
            case R.id.buttonFour:
                inputString += "4";
                break;
            case R.id.buttonFive:
                inputString += "5";
                break;
            case R.id.buttonSix:
                inputString += "6";
                break;
            case R.id.buttonSeven:
                inputString += "7";
                break;
            case R.id.buttonEight:
                inputString += "8";
                break;
            case R.id.buttonNine:
                inputString += "9";
                break;
            case R.id.buttonZero:
                inputString += "0";
                break;
            case R.id.buttonPoint:
                inputString += ".";
                break;
            case R.id.buttonPlus:
                inputString += "+";
                break;
            case R.id.buttonMinus:
                inputString += "-";
                break;
            case R.id.buttonMultiply:
                inputString += "*";
                break;
            case R.id.buttonDivide:
                inputString += "/";
                break;
            case R.id.buttonEquals:
                // Evaluate the string and place the result in output
                Evaluator evaluator = new Evaluator();
                String output;
                try {
                    output = evaluator.evaluate(inputString);
                } catch (EvaluationException e) {
                    e.printStackTrace();
                    output = "Error!";
                }

                //Update the output string and log the action that was taken
                txtOutput.setText(output);
                logAction(inputString + " = " + output);
        }

        txtInput.setText(inputString);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onCalculatorFragmentInteraction(String logString);
    }

}
